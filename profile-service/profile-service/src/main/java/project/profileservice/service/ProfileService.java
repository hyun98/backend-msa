package project.profileservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.profileservice.domain.Badge;
import project.profileservice.domain.ProfileBadge;
import project.profileservice.domain.Profile;
import project.profileservice.repository.BadgeRepository;
import project.profileservice.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final BadgeRepository badgeRepository;

    @Transactional
    public Long createProfile(Long user_id) {
        Profile profile = new Profile();
        profile.setUser_id(user_id);
        profile.setStrick(1);
        profileRepository.save(profile);
        
        return user_id;
    }

    
    /**
     * 비즈니스 로직
     * profile 추가
     */
    

    @Transactional
    public Long addBadge(Long profile_id, Long badge_id) {
        //엔티티 조회
        Profile profile = profileRepository.findOne_ByProfileId(profile_id);
        Badge badge = badgeRepository.findOne(badge_id);

        //ProfileBadge 생성
        ProfileBadge profileBadge = ProfileBadge.CreateProfileBadge(badge);

        //profile 생성
        profile.AddProfileBadge(profileBadge);
        profileRepository.save(profile);
        return profile.getId();
    }
}
