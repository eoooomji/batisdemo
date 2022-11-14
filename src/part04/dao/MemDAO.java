package part04.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import part04.dto.MemDTO;

public class MemDAO {
	private SqlSession session;

	public MemDAO() {
		String resource = "config/configuration.xml";

		try (Reader reader = Resources.getResourceAsReader(resource)) {

			SqlSessionFactoryBuilder sqlBuilder = new SqlSessionFactoryBuilder();

			SqlSessionFactory factory = sqlBuilder.build(reader);

			session = factory.openSession(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end MemDAO()

	public List<MemDTO> allMethod() {
		return session.selectList("per.all");
	}

	public int keyMethod() {
		return session.selectOne("per.keyNum");
	}

	public int multiInsertMethod(List<MemDTO> list) {
		return session.insert("per.multiIns", list);
	}

	public int multiDeleteMethod(List<Integer> list) {
		return session.delete("per.multiDel", list);
	}

	public List<MemDTO> searchMethod(MemDTO dto) {
		return session.selectList("per.search", dto);
	}

	public int multiUpdateMethod(MemDTO dto) {
		return session.update("per.multiUpt", dto);
	}

	public int insertDataMethod(MemDTO dto) {
		return session.insert("per.insData", dto);
	}

} // end class
