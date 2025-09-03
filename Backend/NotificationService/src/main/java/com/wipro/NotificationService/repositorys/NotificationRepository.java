package com.wipro.NotificationService.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.NotificationService.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
