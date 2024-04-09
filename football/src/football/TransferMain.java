package football;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransferMain {
	public static Scanner sc = new Scanner(System.in);
	private static User user;
	private static Team team;
	private static Admin admin = new Admin();
	private static ArrayList<User> userList = new ArrayList<User>();
	private static ArrayList<Team> teamList = new ArrayList<Team>();
	private static ArrayList<Player> playerList = new ArrayList<Player>();

	public static boolean isLogin = false;
	public static boolean isAdmin = false;

	public static void main(String[] args) {
		boolean exit = false;
		String menuStr = null;
		String email = null;
		String password = null;
		int menuNum = 0;

		playerTeamList();

		while (!exit) {
			if (!isLogin) {
				System.out.println("================Transfer Market================");
				System.out.println("     1.회원가입 \t        2.로그인");
				System.out.println("     3.관리자 로그인 \t4.메뉴 종료");
				System.out.println("===============================================");
				System.out.println("메뉴 선택");
				menuNum = sc.nextInt();
				sc.nextLine();
				switch (menuNum) {
				case 1:
					menuStr = "회원가입";
					break;
				case 2:
					menuStr = "로그인";
					break;
				case 3:
					menuStr = "관리자 로그인";
					break;
				case 4:
					menuStr = "종료";
					break;
				}
			} else if (isAdmin) {
				System.out.println("1.팀 생성 \t 2.선수생성");
				System.out.println("3.팀 삭제 \t 4.선수삭제");
				System.out.println("5.관리자 메뉴 종료");
				menuNum = sc.nextInt();
				sc.nextLine();
				switch (menuNum) {
				case 1:
					menuStr = "팀 생성";
					break;
				case 2:
					menuStr = "선수생성";
					break;
				case 3:
					menuStr = "팀 삭제";
					break;
				case 4:
					menuStr = "선수삭제";
					break;
				case 5:
					menuStr = "종료";
					break;
				}
			} else {
				System.out.println("============================");
				System.out.println("1.팀관리   \t2.팀 목록");
				System.out.println("3.이적시장  \t4.종료");
				System.out.println("============================");
				menuNum = sc.nextInt();
				sc.nextLine();
				switch (menuNum) {
				case 1:
					System.out.println("============================");
					System.out.println("1.선수방출 \t 2.선수 판매");
					System.out.println("3.선수 영입");
					System.out.println("============================");
					int teamManagementNum = sc.nextInt();
					sc.nextLine();
					switch (teamManagementNum) {
					case 1:
						menuStr = "선수 방출";
						break;
					case 2:
						menuStr = "선수 판매";
						break;
					case 3:
						menuStr = "선수 영입";
						break;
					}
					break;
				case 2:
					menuStr = "팀 목록";
					break;
				case 3:
					menuStr = "이적시장";
					break;
				case 4:
					menuStr = "종료";
					break;
				}
			}
			switch (menuStr) {
			case "회원가입":
				joinMemberShip();
				break;
			case "로그인":
				login(email, password);
				break;
			case "관리자 로그인":
				adminLogin();
				isLogin = true;
				isAdmin = true;
				break;
			case "종료":
				menuExit();
				exit = true;
				break;
			case "팀 생성":
				createTeam();
				break;
			case "선수생성":
				createPlayer();
				break;
			case "팀 삭제":
				removeTeam();
				break;
			case "선수삭제":
				deletePlayer();
				break;
			case "선수 방출":
				playerRelease();
				break;
			case "선수 판매":
				playerSale();
				break;
			case "선수 영입":
				playerRecruitment();
				break;
			case "팀 목록":
				teamList();
				break;
			case "이적시장":
				transferMarket();
				break;
			}
		}

	}

	// 이적시장
	private static void transferMarket() {

	}

	// 팀목록
	private static void teamList() {
		
	}

	// 선수영입
	private static void playerRecruitment() {
		
	}

	// 선수판매
	private static void playerSale() {

	}

	// 선수방출
	private static void playerRelease() {

	}

	// 선수삭제
	private static void deletePlayer() {
		System.out.println("삭제할 선수 이름를 입력하세요.");
		String playerName = sc.nextLine();

		System.out.println("삭제할 선수 등번호를 입력하세요.");
		int backNum = sc.nextInt();
		sc.nextLine();

		boolean playerFound = false;

		for (int i = 0; i < playerList.size(); i++) {
			Player player = playerList.get(i);
			if (player.getName().equals(playerName) && player.getBackNum() == backNum) {
				playerFound = true;
				playerList.remove(player);
				System.out.println("선수 삭제가 완료되었습니다.");
				break;
			}
		}
		if (!playerFound) {
			System.out.println("삭제할 선수를 찾지 못하였습니다.");
			return;
		}

		// 파일저장
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("player.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			oos.writeObject(playerList);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 팀삭제
	private static void removeTeam() {
		System.out.println("삭제할 팀을 입력하세요.");
		String teamName = sc.nextLine();

		boolean teamFound = false;

		for (Team data : teamList) {
			if (data.getTeamName().equals(teamName)) {
				teamFound = true;
				teamList.remove(data);
				writeTeamFileObject();
				System.out.println("팀 삭제가 완료되었습니다.");
				return;
			}
		}
		if (!teamFound) {
			System.out.println("삭제할 팀이 존재하지 않습니다.");
			return;
		}
	}

	// 선수생성
	private static void createPlayer() {
		System.out.println("생성할 선수 이름를 입력하세요.");
		String playerName = sc.nextLine();

		System.out.println("생성할 선수 등번호를 입력하세요.");
		int backNum = sc.nextInt();
		sc.nextLine();

		System.out.println("생성할 선수 슛 능력치를 입력하세요.");
		int shout = sc.nextInt();
		sc.nextLine();

		System.out.println("생성할 선수 패스 능력치를 입력하세요.");
		int pass = sc.nextInt();
		sc.nextLine();

		System.out.println("생성할 선수 수비 능력치를 입력하세요.");
		int def = sc.nextInt();
		sc.nextLine();

		System.out.println("생성할 선수 포지션을 입력하세요.");
		String position = sc.nextLine();

		System.out.println("생성할 선수 몸값을 입력하세요.");
		int price = sc.nextInt();
		sc.nextLine();

		Player player = new Player(playerName, backNum, shout, pass, def, position, price);

		playerList.add(player);
		System.out.println("선수생성이 완료되었습니다.");

		// 파일저장
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("player.txt")));
			oos.writeObject(playerList);
		} catch (FileNotFoundException e) {
			System.out.println("저장할 파일을 찾지 못하였습니다.");
		} catch (IOException e) {
			System.out.println("파일 저장을 실패하였습니다.");
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 팀생성
	private static void createTeam() {
		System.out.println("생성할 팀을 입력하세요.");
		String teamName = sc.nextLine();

		Team team = new Team(teamName);

		teamList.add(team);
		System.out.println("팀 생성 완료되었습니다.");
		// 파일저장
		writeTeamFileObject();

	}

	// 관리자 로그인
	private static void adminLogin() {
		System.out.println("관리자 정보를 입력하세요.");

		System.out.println("아이디 ");
		String adminId = sc.nextLine();

		System.out.println("비밀번호 ");
		String adminpw = sc.nextLine();

		if (admin.adminLogin(adminId, adminpw)) {
			System.out.println("관리자 로그인 하였습니다.");
		} else {
			System.out.println("관리자 로그인을 실패하였습니다.");
		}
	}

	// 종료
	private static void menuExit() {

		System.out.println("종료하였습니다.");
	}

	// 로그인
	private static void login(String email, String passWord) {
		readUserFileObject();

		System.out.println("이메일 아이디를 입력하세요");
		String loginId = sc.nextLine();
		System.out.println("비밀번호를 입력하세요.");
		String loginPw = sc.nextLine();

		boolean loggedId = false;

		for (User data : userList) {
			if (data.getIemailId().equals(loginId) && data.getPw().equals(loginPw)) {
				loggedId = true;
				break;
			}
		}
		if (loggedId) {
			System.out.println("로그인 성공하였습니다.");
			isLogin = true;
		} else {
			System.out.println("로그인 실패하였습니다.");
		}
	}

	// 회원가입
	public static void joinMemberShip() {
		readUserFileObject();
		String email = null;
		String passWord = null;
		System.out.println("사용하실 아이디를 입력 하세요.");
		email = sc.nextLine();
		System.out.println("사용하실 비밀번호를 입력하세요");
		passWord = sc.nextLine();

		for (User data : userList) {
			if (data.getIemailId().equals(email)) {
				System.out.println("존재하는 아이디가 있습니다.");
				return;
			}
		}

		for (int i = 0; i < teamList.size(); i++) {
			System.out.println(i + 1 + ". " + teamList.get(i).getTeamName());
		}

		System.out.println("사용하실 팀을 선택하세요.");
		String teamName = sc.nextLine();

		ArrayList<Player> players = null;
		for (Team team : teamList) {
			if (team.getTeamName().equals(teamName)) {
				players = team.getPlayer();
				break;
			}
		}
		if (players != null) {
			System.out.println("회원가입이 완료되었습니다.");
			userList.add(new User(email, passWord, teamName, players));
			writeUserFileObject();
		} else {
			System.out.println("회원가입이 실패하였습니다.");
		}

	}

	// 유저가 사용할 팀 리스트
	private static void playerTeamList() {
		List<Team> teamplayerList = new ArrayList<>();
		Team team1 = new Team("Tottenham Spurs");
		team1.addPlayer(new Player("Heung Min Son", 7, 98, 80, 49, "fw", 98));
		team1.addPlayer(new Player("Richarlison", 9, 79, 72, 52, "fw", 46));
		team1.addPlayer(new Player("Brennan Johnson", 22, 74, 67, 44, "fw", 36));
		team1.addPlayer(new Player("James Maddison", 10, 81, 86, 63, "mf", 88));
		team1.addPlayer(new Player("Rodrigo Bentancur", 30, 67, 81, 67, "mf", 39));
		team1.addPlayer(new Player("Pierre-Emile Højbjerg", 5, 73, 78, 69, "mf", 52));
		team1.addPlayer(new Player("Pedro Porro", 23, 73, 77, 89, "df", 44));
		team1.addPlayer(new Player("Guglielmo Vicario", 13, 98, 80, 91, "gk", 39));
		team1.addPlayer(new Player("Cristian Romero", 17, 46, 59, 82, "df", 85));
		team1.addPlayer(new Player("Micky van de Ven", 37, 43, 59, 87, "df", 26));
		team1.addPlayer(new Player("Destiny Udogie", 4, 63, 69, 80, "df", 39));
		teamList.add(team1);

		Team team2 = new Team("Manchester City");
		team2.addPlayer(new Player("Erling Haaland", 9, 93, 66, 46, "fw", 195));
		team2.addPlayer(new Player("Phil Foden", 47, 79, 82, 42, "fw", 117));
		team2.addPlayer(new Player("Jack Grealishn", 10, 76, 84, 49, "fw", 156));
		team2.addPlayer(new Player("Kevin De Bruyne", 17, 88, 94, 67, "mf", 208));
		team2.addPlayer(new Player("Rodri", 16, 73, 80, 62, "mf", 114));
		team2.addPlayer(new Player("Bernardo Silva", 20, 78, 86, 65, "mf", 156));
		team2.addPlayer(new Player("Kyle Walker", 2, 63, 77, 83, "df", 91));
		team2.addPlayer(new Player("Nathan Aké", 6, 53, 72, 87, "df", 83));
		team2.addPlayer(new Player("Joško Gvardiol", 24, 54, 69, 89, "df", 104));
		team2.addPlayer(new Player("Sergio Gómez", 21, 70, 75, 81, "df", 39));
		team2.addPlayer(new Player("Stefan Ortega", 18, 73, 86, 92, "gk", 28));
		teamList.add(team2);

		Team team3 = new Team("Fc Barcelona");
		team3.addPlayer(new Player("Lewandowski", 9, 91, 80, 44, "fw", 120));
		team3.addPlayer(new Player("Lamin Yamal", 27, 95, 80, 45, "fw", 125));
		team3.addPlayer(new Player("Joao Felix", 14, 79, 78, 40, "fw", 50));
		team3.addPlayer(new Player("Frenkie DeJong", 21, 69, 86, 77, "mf", 130));
		team3.addPlayer(new Player("Ilkay Gundogan", 22, 81, 84, 73, "mf", 85));
		team3.addPlayer(new Player("Pedri", 8, 69, 82, 70, "mf", 130));
		team3.addPlayer(new Player("Balde", 3, 79, 78, 35, "df", 115));
		team3.addPlayer(new Player("Jules Kounde", 23, 45, 68, 86, "df", 95));
		team3.addPlayer(new Player("Ronald Araujo", 4, 51, 65, 86, "df", 115));
		team3.addPlayer(new Player("Joao Cancelo", 2, 73, 85, 80, "df", 80));
		team3.addPlayer(new Player("Marc-Andre Ter Stergen", 1, 85, 89, 47, "gk", 115));
		teamList.add(team3);

		// 팀 리스트 저장하기
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("team.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			oos.writeObject(teamList);
		} catch (IOException e) {
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 유저 파일저장
	private static void writeUserFileObject() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("user.txt")));
			oos.writeObject(userList);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 팀 파일 저장
	private static void writeTeamFileObject() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File("team.txt")));
			oos.writeObject(teamList);
			System.out.println("파일저장 성공");
		} catch (FileNotFoundException e) {
			System.out.println("파일을 저장하지 못하였습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 유저 파일 읽어오기
	private static void readUserFileObject() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("user.txt")))) {
			userList = (ArrayList<User>) ois.readObject();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}

	// 팀 파일 읽어오기
	private static void readTeamFileObject() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("team.txt")));
			teamList = (ArrayList<Team>) ois.readObject();

		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾지 못하였습니다.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("파일을 찾지 못하였습니다.");
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}