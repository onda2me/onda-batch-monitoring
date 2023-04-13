package com.onda2me.app.util;

/**
 * 게시글의 페이징 처리를 위한 유틸리티 클래스
 * 
 * @author Administrator
 *
 */
public class PageUtil {

    /** 전체 문서 수 */
	private int total = 0;

	/** 한 페이지당 출력될 문서 수 */
	private int listPerPage = 20;

	/** 바로가기 페이지 수 */
	private int maxPage = 0;

	/** 현재 페이지 번호 */
	private int pageNo = 0;
	
	private String currPage = "1";

	/** 링크 주소 */
    private String linkAct = null;
    
	/** 링크 파라미터 */
	private String linkParams = null;

	/** 이전 페이지 버튼 */
	private String prevButton = " &laquo; 이전 ";

	/** 다음 페이지 버튼 */
	private String nextButton = " 다음 &raquo; ";

	/** 페이지번호 파라메터 */
	private String pageNoParam = "currPage";
	
	
	public String getPageNoParam() {
		return pageNoParam;
	}

	public void setPageNoParam(String pageNoParam) {
		this.pageNoParam = pageNoParam;
	}
	
	public PageUtil(){
		
	}	

	public void init(int total){

	    setPageNo();
	    init(total, pageNo, IConstants.PAGE_LIST_PER, IConstants.PAGE_MAX_PAGE, "",  null);
	}
	
	public void init(int total, String linkAct, String linkParams){

	    setPageNo();
	    init(total, pageNo, IConstants.PAGE_LIST_PER, IConstants.PAGE_MAX_PAGE, linkAct, linkParams);
	}
	
    public void init(int total, int listPer, String linkAct, String linkParams){

        setPageNo();
        init(total, pageNo, listPer, IConstants.PAGE_MAX_PAGE, linkAct, linkParams);
    }	

	/**
	 * PageUtil init Method 
	 * 
	 * @param total  전체 문서 수
	 * @param pageNo 현재  페이지
	 * @param maxList  한 페이지당 출력될 문서 수
	 * @param maxPage 바로가기 페이지 수
	 * @param linkAct 링크 주소
	 * @param linkParams 링크 파리미터 
	 */	
    public void init(int total, int pageNo, int maxList, int maxPage, String linkAct, String linkParams){
        
        this.total = total;
        this.pageNo = pageNo;
        this.listPerPage = maxList;
        this.maxPage = maxPage;
        this.linkAct = linkAct;
        this.linkParams = linkParams;
    }
    

	/**
	* 전체 문서 수를 설정한다.
	* @param total 전체 문서 수
	*/
	public void setTotal(int total){
		this.total = total;
	}

	/**
	* 전체 문서 수를 반환한다.
	* @return int 전체 문서수
	*/
	public int getTotal(){
		return total;
	}

	/**
	* 한 페이지당 출력될 문서 수를 설정한다.
	* @param maxList 한 페이지당 출력될 문서 수
	*/
	public void setMaxList(int maxList){
		this.listPerPage = maxList;
	}

	/**
	* 한 페이지당 출력될 문서 수를 반환한다.
	* @return int 한 페이지당 출력될 문서 수
	*/
	public int getMaxList(){
		return listPerPage;
	}

	/**
	* 바로가기 페이지 수를 설정한다.
	* @param maxPage 바로가기 페이지 수
	*/
	public void setMaxPage(int maxPage){
		this.maxPage = maxPage;
	}

	/**
	* 바로가기 페이지 수를 반환한다.
	* @return int 바로가기 페이지 수
	*/
	public int getMaxPage(){
		return maxPage;
	}
	
	public void setCurrPage(String currPage){
		this.currPage = StringUtil.nullToString(currPage, "1");
		
	}
	
	/**
	* 현재 페이지 번호를 설정한다.
	* @param pageNo 현재 페이지 번호
	*/	
	public void setPageNo(){
		this.pageNo = StringUtil.nullToInteger(this.currPage, 1);
	}


	/**
	* 현재 페이지 번호를 반환한다.
	* @return int 현재 페이지 번호
	*/
	public int getPageNo(){
		return pageNo;
	}

	/**
	* 링크 주소를 설정한다.
	* @param linkAct 링크 주소
	*/
	public void setLinkAct(String linkAct){
		this.linkAct = linkAct;
	}

	/**
	* 링크 주소를 반환한다.
	* @return String 링크 주소
	*/
	public String getLinkAct(){
		return linkAct;
	}

    /**
    * 링크 파라미터를 설정한다.
    * @param linkAct 링크 파라미터
    */
    public void setLinkParams(String linkParams){
        this.linkParams = linkParams;
    }

    /**
    * 링크 파라미터를 반환한다.
    * @return String 링크 파라미터
    */
    public String getLinkParams(){
        return linkParams;
    }
    
	/**
	* 전체 페이지수를 반환한다.
	* @return int 전체 페이지 수
	*/
	public int getTotalPage(){
		return total%listPerPage==0 ? total/listPerPage : total/listPerPage+1;
	}

	/**
	* 현재 페이지 목록의 시작 번호를 구한다.
	* @return 페이지 목록 시작 번호
	*/
	public int getStartNo(){
		return (pageNo * listPerPage) - listPerPage;
	}

	
	/**
	* 현재 페이지 목록의 종료 번호를 구한다.
	* @return 페이지 목록 종료 번호
	*/
	public int getEndNo(){
	    //ORACLE, MSSQL
		//return (pageNo * listPerPage > total)? total : (pageNo * listPerPage);

	    //MYSQL
	    return (pageNo * listPerPage > total)? total : listPerPage;
	}

	/**
	* 페이지 목록 출력시 번호영역을 순번을 구한다.
	* @return int 페이지 목록 시작 순번
	*/
	public int getSeqNo(int idx){
		return total - listPerPage*(pageNo-1) - idx +1;
	}

	/**
     * @return Returns the nextButton.
     */
    public String getNextButton() {
        return nextButton;
    }
    /**
     * @param nextButton The nextButton to set.
     */
    public void setNextButton(String nextButton) {
        this.nextButton = nextButton;
    }
    /**
     * @return Returns the prevButton.
     */
    public String getPrevButton() {
        return prevButton;
    }
    /**
     * @param prevButton The prevButton to set.
     */
    public void setPrevButton(String prevButton) {
        this.prevButton = prevButton;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        
        str
            .append("[")
            .append("total=").append(total).append(", ")
            .append("currPage=").append(currPage).append(", ")
            .append("pageNo=").append(pageNo).append(", ")
            .append("startNo=").append(getStartNo()).append(", ")
            .append("endNo=").append(getEndNo()).append(", ")
            .append("seqNo=").append(getSeqNo(pageNo))
            .append("]");
        
        
        return str.toString();
    }

//    public String printTotalCount() {
//
//		StringBuffer buf = new StringBuffer();
//		
//		buf
//			.append("<div class='kt-padding-10'>")
//			.append("<span class='kt-font-bolder kt-font-lg'> 총 ")
//			.append(this.getTotal()).append(" 건 </span>")
//			.append("</div>");
//		
//		return buf.toString();		
//    }
	
	/**
	* 페이징 처리된 문자열을 출력한다.
	* @return String 설정된 값에 의하여 페이징을 구현한 문자열을 반환한다.
	*/
	public String printPageList() {
		int totalPage = getTotalPage();
		int startPage;
		int endPage;
		int curPage;
		String linkPath;
		
/*		
		
        <ul class="pagination">
            <li class="page-item disabled"><a class="page-link" href="#">이전</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">다음</a></li>
        </ul>
*/
		StringBuffer buf = new StringBuffer();
		buf.append("<div class='row'><span class='col-3'> 총 ").append(this.getTotal()).append(" 건 </span>");
		buf.append("<nav class='col-9' ><ul class=\"pagination\">");
		
		
		if(getTotalPage() > 1){

			if(linkAct.indexOf("?") == -1)
			    linkPath = linkAct + "?" + String.valueOf(linkParams);
			else
			    linkPath = linkAct + "&"+ String.valueOf(linkParams);

			// 시작페이지와 마지막페이지
			startPage = (int)((pageNo - 1) / maxPage) * maxPage + 1;
			endPage= (int)((((startPage - 1) + maxPage) / maxPage) * maxPage);

			if (totalPage <= endPage)
				endPage = totalPage;

			if (pageNo > maxPage){
				curPage = startPage -1;
				buf
					.append("<li class='page-item'><a class='page-link' href='").append(linkPath).append(pageNoParam).append("=").append(curPage).append("'>")
					.append(prevButton).append("</a></li>");
			}else{
				buf
					.append("<li class='page-item'><span class='page-link'>").append(prevButton).append("</span></li>");				
			}

			curPage = startPage;
			while(curPage<=endPage){
				if (curPage == pageNo){
					if (totalPage != 1){
						if(curPage == endPage){
							buf
							.append("<li class='page-item active '><a class='page-link' href='").append(linkPath).append(pageNoParam).append("=").append(curPage).append("'>")
							.append(StringUtil.leftPad(String.valueOf(curPage), 2, "0"))
							.append("</a></li>");
						}else{
							buf
							.append("<li class='page-item active'><a class='page-link' href='").append(linkPath).append(pageNoParam).append("=").append(curPage).append("'>")
							.append(StringUtil.leftPad(String.valueOf(curPage), 2, "0"))
							.append("</a></li>");
						}
					}
				}else {
					if (curPage == endPage){
						buf
						.append("<li class='page-item'><a class='page-link' href='").append(linkPath).append(pageNoParam).append("=").append(curPage).append("'>")
						.append(StringUtil.leftPad(String.valueOf(curPage), 2, "0")).append("</a></li>");
					}else{
						buf
						.append("<li class='page-item'><a class='page-link' href='").append(linkPath).append(pageNoParam).append("=").append(curPage).append("'>")
						.append(StringUtil.leftPad(String.valueOf(curPage), 2, "0")).append("</a></li>");
					}
				}

				curPage++;
			}

			if (totalPage > endPage){
				curPage = endPage + 1;
				buf
				.append("<li class='page-item'><a class='page-link' href='").append(linkPath).append(pageNoParam).append("=").append(curPage).append("'>")
				.append(nextButton).append("</a></li>");
			}else{
				buf
				.append("<li class='page-item'><span class='page-link'>").append(nextButton).append("</span></li>");
				
			}
		}

		buf.append("</ul></nav></div>");
		
		//logger.info(buf.toString());
		return buf.toString();
	}
}
