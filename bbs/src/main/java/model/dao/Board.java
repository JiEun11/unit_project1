package model.dao;

import java.util.ArrayList;

import model.vo.BoardVO;

public interface Board {
	public ArrayList<BoardVO> listAll();
	public boolean insert(BoardVO vo);
	public ArrayList<BoardVO> searchTitle(String keyword);
	public ArrayList<BoardVO> searchWriter(String keyword);
	public ArrayList<BoardVO> searchContent(String keyword);
	public boolean delete(int num);
	public boolean update(BoardVO vo);
	public ArrayList<BoardVO> pagenation(String table, int start, int end);
	public int pageCnt(String table);
}