package ir.maktab.service;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.FeedbackDto;
import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.entity.order.Feedback;

/**
 * @author Negin Mousavi
 */
public interface FeedbackService {
    void save(Feedback feedback);

    Long getCountOfRecords();

    void addFeedback(FeedbackDto feedbackDto, String score, String comment);

    FeedbackDto getByExpertAndOrder(ExpertDto expert, OrderDto order);
}
