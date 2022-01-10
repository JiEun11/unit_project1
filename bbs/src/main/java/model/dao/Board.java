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
	public ArrayList<BoardVO> pagenation(String whereParam, String keyword, int start, int end);
	public int pageCntAll();
	public int pageCntTitle(String keyword);
	public int pageCntWriter(String keyword);
	public int pageCntContent(String keyword);
}