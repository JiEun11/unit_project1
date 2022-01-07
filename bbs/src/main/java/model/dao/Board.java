package model.dao;

import java.util.ArrayList;
import model.vo.BoardVO;

public interface Board {
	public ArrayList<BoardVO> listAll();
	public boolean insert(BoardVO vo);
	public ArrayList<BoardVO> search(String keyword);
	public boolean delete(int num);
	public boolean update(BoardVO vo);
}
