package api;

import entities.HotelClient;
import entities.dto.HotelClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.expression.ParseException;

public class ControllerUtils {
    static HotelClient convertToEntityHotelClient(HotelClientDTO postDto) throws ParseException {
        ModelMapper mapper = new ModelMapper();
        HotelClient post = mapper.map(postDto, HotelClient.class);
//        post.setSubmissionDate(postDto.getSubmissionDateConverted(
//                userService.getCurrentUser().getPreference().getTimezone()));
//
//        if (postDto.getId() != null) {
//            Post oldPost = postService.getPostById(postDto.getId());
//            post.setRedditID(oldPost.getRedditID());
//            post.setSent(oldPost.isSent());
//        }
        return post;
    }
}
