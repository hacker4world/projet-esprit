package com.example.futurbe.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
@Service
public class ReminderService {

    private  EmailService emailService;
//    private ClubEventRepository eventRepository; // Assume you have an EventRepository

    public ReminderService(EmailService emailService) {
        this.emailService = emailService;
//        this.eventRepository = eventRepository;
    }

    @Scheduled(cron = "0 0 9 * * *") // Execute every day at 9 AM
    public void sendDailyReminders() {
        // Logic to get upcoming events and send reminders
        // Example:
        // List<Event> upcomingEvents = eventRepository.findUpcomingEvents();
        // for (Event event : upcomingEvents) {
        //     emailService.sendEmail(...);
        // }
    }
}
