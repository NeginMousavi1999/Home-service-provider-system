package ir.maktab.service;

import ir.maktab.model.members.Expert;

/**
 * @author Negin Mousavi
 */
public interface ExpertService {
    void save(Expert expert);

    boolean delete(Expert expert);

    boolean update(Expert expert);

    Expert findByEmail(String email);

    Long getCountOfRecords();
}
