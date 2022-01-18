package project.questionboard.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter @RequiredArgsConstructor
public class Posting {
	@Id @Column(name = "posting_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private String code;

	@Column(name = "created_dt")
	private LocalDateTime createDate;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Posting(String title, String content, String code, User writer) {
		this.title = title;
		this.content = content;
		this.code = code;
		this.user = writer;
		this.createDate = LocalDateTime.now().plusHours(9L);
	}
}
