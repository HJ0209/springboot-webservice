package springboot.domain.posts;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
		
	@Test
	public void 게시글저장_불러오기() {
		String title = "테스트 제목";
		String content = "테스트 내용";
		
		postsRepository.save(Posts.builder().title(title).content(content).author("諛뺤갹�졃").build());
		List<Posts> postsList = postsRepository.findAll();
		
		Posts posts = postsList.get(0);	
		Assertions.assertThat(posts.getTitle()).isEqualTo(title);
		Assertions.assertThat(posts.getContent()).isEqualTo(content);
	}
	
	@Test
	public void BaseTimeEntit() {
		// given
		LocalDateTime now = LocalDateTime.now();		
		postsRepository.save(Posts.builder().title("title").content("content").author("author").build());
		
		// when
		List<Posts> postsList = postsRepository.findAll();
		
		// then
		Posts posts = postsList.get(0);
		Assertions.assertThat(posts.getCreatedDate()).isAfter(now);
		Assertions.assertThat(posts.getModifiedDate()).isAfter(now);
	}

}
