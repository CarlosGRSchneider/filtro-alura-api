package br.com.cursosalura.filtro_alura.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ApiRefreshService {


    @Autowired
    private AluraFeignClient client;

    @Scheduled(fixedDelayString = "1", timeUnit = TimeUnit.DAYS)
    public void refreshCourses() {

        Database.clear();

        List<Course> courses = client.getCourses();

        System.out.println("Fetched courses: " + courses.size());
        System.out.println("End of schedule");

        Database.addAll(courses);
    }
}
