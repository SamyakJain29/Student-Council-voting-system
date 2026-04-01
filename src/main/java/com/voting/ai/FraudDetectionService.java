package com.voting.ai;

import com.voting.model.Vote;
import com.voting.repository.SuspiciousLogRepository;
import com.voting.repository.VoteRepository;

import java.sql.Timestamp;
import java.util.List;

public class FraudDetectionService {
    
    private final SuspiciousLogRepository logRepository;
    private final VoteRepository voteRepository;
    
    // Configurable thresholds for the rule-based AI
    private static final int MAX_VOTES_PER_IP = 3;
    private static final long FAST_VOTING_THRESHOLD_MS = 5000; // 5 seconds between votes from same IP is suspicious

    public FraudDetectionService() {
        this.logRepository = new SuspiciousLogRepository();
        this.voteRepository = new VoteRepository();
    }

    /**
     * AI-based rule engine to detect fraudulent voting attempts.
     * Returns true if the vote attempt is flagged as fraudulent.
     */
    public boolean isFraudulentVoteAttempt(int userId, String ipAddress, String systemInfo) {
        boolean isFraud = false;
        StringBuilder reasonBuilder = new StringBuilder();

        // Rule 1: Multi-vote attempt from same user is handled by DB unique constraints / VoteService,
        // but this AI layer can double check and log it.
        if (voteRepository.hasUserVoted(userId)) {
            reasonBuilder.append("User already voted. ");
            isFraud = true;
        }

        // Rule 2: IP Address velocity and volume check
        if (ipAddress != null && !ipAddress.isEmpty()) {
            List<Vote> votesFromIp = voteRepository.getVotesByIpAddress(ipAddress);
            
            // Check volume
            if (votesFromIp.size() >= MAX_VOTES_PER_IP) {
                reasonBuilder.append(String.format("Too many votes (%d) from IP %s. ", votesFromIp.size(), ipAddress));
                isFraud = true;
            }
            
            // Check velocity (unusually fast voting patterns)
            if (!votesFromIp.isEmpty()) {
                Vote lastVote = votesFromIp.get(votesFromIp.size() - 1);
                long timeSinceLastVote = System.currentTimeMillis() - lastVote.getTimestamp().getTime();
                if (timeSinceLastVote < FAST_VOTING_THRESHOLD_MS) {
                    reasonBuilder.append("Unusually fast voting pattern detected (Bots/Scripts). ");
                    isFraud = true;
                }
            }
        }
        
        // Rule 3: System Info heuristic (Bot detection based on missing/generic User-Agent equivalent)
        if (systemInfo == null || systemInfo.trim().isEmpty() || systemInfo.toLowerCase().contains("bot") || systemInfo.toLowerCase().contains("curl")) {
            reasonBuilder.append("Suspicious system info/user-agent. ");
            isFraud = true;
        }

        if (isFraud) {
            logRepository.logSuspiciousActivity(userId, reasonBuilder.toString().trim());
            System.err.println("FRAUD DETECTED: " + reasonBuilder.toString());
        }

        return isFraud;
    }
}
