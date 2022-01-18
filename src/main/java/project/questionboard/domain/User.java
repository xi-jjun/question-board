package project.questionboard.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter @RequiredArgsConstructor
public class User {
	@Id @Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private String password;

	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "user_rank")
	private UserRank userRank;

	@Column(name = "user_created_dt")
	private LocalDateTime createDate;

	@OneToMany(mappedBy = "user")
	private List<Posting> postings = new ArrayList<>();

	public User(String name, String password, String nickName, UserRank userRank) {
		this.name = name;
		this.password = password;
		this.nickName = nickName;
		this.userRank = userRank;
		this.createDate = LocalDateTime.now().plusHours(9L);
	}

	public boolean isSameNickName(String nickName) {
		return this.nickName.equals(nickName);
	}

	public boolean correctPassword(String password) {
		return this.password.equals(password);
	}
}
