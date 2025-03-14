package com.customer.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.service.model.Complaint;
import com.customer.service.repository.ComplaintRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Optional<Complaint> getComplaintById(Long id) {
        return complaintRepository.findById(id);
    }

    public Complaint createComplaint(Complaint complaint) {
        complaint.setStatus("Open");
        return complaintRepository.save(complaint);
    }

    public Complaint updateComplaint(Long id, Complaint complaintDetails) {
        Optional<Complaint> complaint = complaintRepository.findById(id);
        if(complaint.isPresent()){
            Complaint existingComplaint = complaint.get();
            existingComplaint.setDescription(complaintDetails.getDescription());
            existingComplaint.setStatus(complaintDetails.getStatus());
            existingComplaint.setCustomer(complaintDetails.getCustomer());
            return complaintRepository.save(existingComplaint);
        }
        return null;
    }

    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }

    public List<Complaint> getComplaintsByCustomerId(Long customerId) {
        return complaintRepository.findByCustomerId(customerId);
    }
}
