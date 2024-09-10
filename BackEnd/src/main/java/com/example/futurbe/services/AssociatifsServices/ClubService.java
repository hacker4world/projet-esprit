    package com.example.futurbe.services.AssociatifsServices;

    import com.example.futurbe.entitys.AssociatifsEntity.Club;
    import com.example.futurbe.entitys.AssociatifsEntity.ClubMember;
    import com.example.futurbe.repositorys.AssociatifsRepo.ClubMemberRepository;
    import com.example.futurbe.repositorys.AssociatifsRepo.ClubRepository;
    import com.example.futurbe.services.iservices.AssociatifsIServices.IClubService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;
    @Service
    public class ClubService implements IClubService {

        @Autowired
        private ClubRepository clubRepository;

        @Override
        public List<Club> getAllClubs() {
            return clubRepository.findAll();
        }

        @Override
        public Optional<Club> getClubById(Long id) {
            return clubRepository.findById(id);
        }

        @Override
        public Club createClub(Club club) {

                return clubRepository.save(club);
        }

        @Override
        public void deleteClub(Long id) {
            try {
                clubRepository.deleteById(id);
            } catch (Exception e) {
                e.printStackTrace(); // Log the error
                throw new RuntimeException("Error deleting club: " + e.getMessage());
            }
        }
        @Autowired
        private ClubMemberRepository clubMemberRepository;

        // Autres méthodes CRUD

        public List<ClubMember> getMembersByClub(Long clubId) {
            return clubMemberRepository.findByClubId(clubId);
        }
    }
