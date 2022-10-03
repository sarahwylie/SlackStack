package com.cooksys.cookslack.data;

import com.cooksys.cookslack.data.model.entities.*;
import com.cooksys.cookslack.data.model.entities.embeds.Credentials;
import com.cooksys.cookslack.data.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final AnnouncementRepository announcementRepository;

    @Override
    public void run(String... args) throws Exception {

        // --- Company 1 ---
        Company company1 = new Company();
        company1.setName("Cook Systems");
        company1.setDescription("A tech company based in Memphis, TN");

        // --- Company 2 ---
        Company company2 = new Company();
        company2.setName("Google");
        company2.setDescription("A tech company specializing in search and email.");

        // --- Company 3 ---
        Company company3 = new Company();
        company3.setName("Apple");
        company3.setDescription("A tech company based in Cupertino, CA.");

        companyRepository.saveAllAndFlush(Arrays.asList(company1, company2, company3));

        // --- Team 1 ---
        Team team1 = new Team();
        team1.setName("Team One");
        team1.setDescription("Frontend Team");
        team1.setCompany(company1);

        // --- Team 2 ---
        Team team2 = new Team();
        team2.setName("Team Two");
        team2.setDescription("Backend Team");
        team2.setCompany(company1);

        teamRepository.saveAllAndFlush(Arrays.asList(team1, team2));

        // --- User 1 ---
        // Credentials
        Credentials user1Cred = new Credentials();
        user1Cred.setUsername("username1");
        user1Cred.setPassword("password");
        user1Cred.setAdmin(true);

        User user1 = new User();
        user1.setCredentials(user1Cred);
        user1.setFirstName("Cleetus");
        user1.setLastName("McBumble");
        user1.setEmail("email@test.com");
        user1.setPhoneNumber("1234567890");
        user1.setActive(true);
        user1.setStatus("On Vacation");
        user1.setCompany(company1);
        user1.setTeam(team1);

        // --- User 2 ---
        // Credentials
        Credentials user2Cred = new Credentials();
        user2Cred.setUsername("username2");
        user2Cred.setPassword("password");

        User user2 = new User();
        user2.setCredentials(user2Cred);
        user2.setFirstName("John");
        user2.setLastName("Smith");
        user2.setEmail("example@test.com");
        user2.setPhoneNumber("1234567890");
        user2.setActive(true);
        user2.setStatus("Working");
        user2.setCompany(company1);
        user2.setTeam(team2);

        // --- User 3 ---
        Credentials user3Cred = new Credentials();
        // Credentials
        user3Cred.setUsername("username3");
        user3Cred.setPassword("zpQUv5$96!@dDn");

        User user3 = new User();
        user3.setCredentials(user3Cred);
        user3.setFirstName("Luigi");
        user3.setLastName("Pizzaiolo");
        user3.setEmail("luigi@email.com");
        user3.setPhoneNumber("1234567890");
        user3.setActive(false);
        user3.setStatus("Working");
        user3.setCompany(company1);
        user3.setTeam(team1);

        // --- User 4 ---
        // Credentials
        Credentials user4Cred = new Credentials();
        user4Cred.setUsername("username4");
        user4Cred.setPassword("^4a3L8VM4N5eRd");

        User user4 = new User();
        user4.setCredentials(user4Cred);
        user4.setFirstName("Nathan");
        user4.setLastName("Drake");
        user4.setEmail("nathan@email.com");
        user4.setPhoneNumber("1234567890");
        user4.setActive(true);
        user4.setStatus("Working");
        user4.setCompany(company2);

        // --- User 5 ---
        // Credentials
        Credentials user5Cred = new Credentials();
        user5Cred.setUsername("username5");
        user5Cred.setPassword("1R37#wo9JP3of^");

        User user5 = new User();
        user5.setCredentials(user5Cred);
        user5.setFirstName("Daemon");
        user5.setLastName("Targaryen");
        user5.setEmail("ikissedmysisandilikedit@email.com");
        user5.setPhoneNumber("1234567890");
        user5.setActive(true);
        user5.setStatus("Working");
        user5.setCompany(company3);

        // --- User 6 ---
        // Credentials
        Credentials user6Cred = new Credentials();
        user6Cred.setUsername("username6");
        user6Cred.setPassword("1r27&wo5Jp3of^");

        User user6 = new User();
        user6.setCredentials(user6Cred);
        user6.setFirstName("BillyBob");
        user6.setLastName("Thornton");
        user6.setEmail("bleh@email.com");
        user6.setPhoneNumber("1234567890");
        user6.setActive(true);
        user6.setStatus("Working");
        user6.setCompany(company1);

        // --- User 7 ---
        // Credentials
        Credentials user7Cred = new Credentials();
        user7Cred.setUsername("username7");
        user7Cred.setPassword("65RsdWEe*34fFS");

        User user7 = new User();
        user7.setCredentials(user7Cred);
        user7.setFirstName("Brian");
        user7.setLastName("Owen");
        user7.setEmail("bowen@email.com");
        user7.setPhoneNumber("1234567890");
        user7.setActive(true);
        user7.setStatus("Working");
        user7.setCompany(company1);

        userRepository.saveAllAndFlush(Arrays.asList(user1, user2, user3, user4, user5, user6, user7));

        // --- Project 1 ---
        Project project1 = new Project();
        project1.setName("Cook-Slack");
        project1.setDescription("A project management application.");
        project1.setActive(true);
        project1.setTeam(team1);

        projectRepository.saveAllAndFlush(Arrays.asList(project1));

        // --- Announcement 1 ---
        Announcement announcement1 = new Announcement();
        announcement1.setTitle("Our newest project!");
        announcement1.setMessage("We are excited to announce our latest endeavour, Cook-Slack!");
        announcement1.setCompany(company1);
        announcement1.setAuthor(user1);

        announcementRepository.saveAllAndFlush(Arrays.asList(announcement1));

    }
}