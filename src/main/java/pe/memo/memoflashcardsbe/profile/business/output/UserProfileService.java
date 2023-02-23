package pe.memo.memoflashcardsbe.profile.business.output;

import pe.memo.memoflashcardsbe.profile.business.domain.SubjectProgress;

import java.util.List;

public interface UserProfileService {

    List<SubjectProgress> getProgressForSubjectsByUserId(Long userId);
}
