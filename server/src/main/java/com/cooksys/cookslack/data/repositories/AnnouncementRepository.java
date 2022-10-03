package com.cooksys.cookslack.data.repositories;

import com.cooksys.cookslack.data.model.entities.Announcement;
import com.cooksys.cookslack.data.model.entities.Company;
import com.cooksys.cookslack.data.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findAllByCompanyAndDeletedFalse(Company company);

    /**
     * Finds all un-deleted <code>Announcement</code>s by <code>author</code>
     * @param author <code>User</code>
     * @return a list of <code>author</code>s
     */
    List<Announcement> findAllByAuthorAndDeletedFalse(User author);

    /**
     * Search for Announcements within a given date range (start, end)
     *
     * @param start a <code>java.sql.Timestamp</code> as the starting date
     * @param end   a <code>java.sql.Timestamp</code> as the ending date
     * @return a list of Announcements
     */
    List<Announcement> findAllByDateBetweenAndDeletedFalse(Timestamp start, Timestamp end);

}
