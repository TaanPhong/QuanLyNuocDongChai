package ptithcm.bean;

public class LapPhieuNhap {
	private Integer MASP;
	private String tenSP;
	private Integer sL;
	private String ngaySX;
	private String hsd;
	public LapPhieuNhap(Integer mASP, String tenSP, Integer sL, String ngaySX, String hsd) {
		super();
		MASP = mASP;
		this.tenSP = tenSP;
		this.sL = sL;
		this.ngaySX = ngaySX;
		this.hsd = hsd;
	}
	public LapPhieuNhap() {
		super();
	}
	public Integer getMASP() {
		return MASP;
	}
	public void setMASP(Integer mASP) {
		MASP = mASP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public Integer getsL() {
		return sL;
	}
	public void setsL(Integer sL) {
		this.sL = sL;
	}
	public String getNgaySX() {
		return ngaySX;
	}
	public void setNgaySX(String ngaySX) {
		this.ngaySX = ngaySX;
	}
	public String getHsd() {
		return hsd;
	}
	public void setHsd(String hsd) {
		this.hsd = hsd;
	}
}
