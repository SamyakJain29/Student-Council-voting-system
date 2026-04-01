package com.voting.service;

import com.voting.ai.FraudDetectionService;
import com.voting.model.Vote;
import com.voting.repository.CandidateRepository;
import com.voting.repository.UserRepository;
import com.voting.repository.VoteRepository;

public class VotingService {

    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final UserRepository userRepository;
    private final FraudDetectionService fraudDetectionService;

    public VotingService() {
        this.voteRepository = new VoteRepository();
        this.candidateRepository = new CandidateRepository();
        this.userRepository = new UserRepository();
        this.fraudDetectionService = new FraudDetectionService();
    }

    public VotingResult castVote(int userId, int candidateId, String ipAddress, String systemInfo) {
        // 1. Check Fraud Rules
        if (fraudDetectionService.isFraudulentVoteAttempt(userId, ipAddress, systemInfo)) {
            return new VotingResult(false, "Fraudulent activity detected. Vote blocked.");
        }

        // 2. Ensure user hasn't voted already (secondary check aside from AI)
        if (voteRepository.hasUserVoted(userId)) {
            return new VotingResult(false, "You have already cast your vote.");
        }

        // 3. Cast the vote
        Vote vote = new Vote(0, userId, candidateId, ipAddress, systemInfo, null);
        boolean success = voteRepository.castVote(vote);
        
        if (success) {
            // 4. Update vote count
            candidateRepository.incrementVoteCount(candidateId);
            // 5. Update user status
            userRepository.updateHasVoted(userId, true);
            AuthenticationService.updateCurrentUserHasVoted();
            return new VotingResult(true, "Vote cast successfully.");
        } else {
            return new VotingResult(false, "System error occurred while voting.");
        }
    }

    public static class VotingResult {
        private final boolean success;
        private final String message;

        public VotingResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
    }
}
