package com.voting.service;

import com.voting.model.Candidate;
import com.voting.repository.CandidateRepository;

import java.util.List;

public class AdminService {

    private final CandidateRepository candidateRepository;

    public AdminService() {
        this.candidateRepository = new CandidateRepository();
    }

    public boolean addCandidate(String name, String department) {
        if (name == null || name.trim().isEmpty() || department == null || department.trim().isEmpty()) {
            return false;
        }
        Candidate candidate = new Candidate(0, name, department, 0, null);
        return candidateRepository.addCandidate(candidate);
    }

    public boolean updateCandidate(int id, String name, String department) {
        Candidate candidate = new Candidate(id, name, department, 0, null);
        return candidateRepository.updateCandidate(candidate);
    }

    public boolean removeCandidate(int candidateId) {
        return candidateRepository.deleteCandidate(candidateId);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.getAllCandidates();
    }
}
