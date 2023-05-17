package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NHANVIEN")
public class NhanVien {
	@Id
	@GeneratedValue
	private Integer MANV;
	private String HO;
	private String TEN;
	private String SDT;
	private String DIACHI;
	private Boolean GIOITINH;
	private String EMAIL;
	private String CMND;

	private String NGSINH;
	private Integer LUONG;
	private Boolean TRANGTHAI;

	@OneToMany(mappedBy = "nhanVien", fetch = FetchType.EAGER)
	private Collection<PhieuNhap> phieuNhap;

	@OneToMany(mappedBy = "nhanVien", fetch = FetchType.EAGER)
	private Collection<HoaDon> hoaDon;

	@OneToMany(mappedBy = "nhanVien", fetch = FetchType.EAGER)
	private Collection<NhanVien> quanLy;
	
	@ManyToOne
	@JoinColumn(name = "MAQL")
	private NhanVien nhanVien;

	@OneToOne
	@JoinColumn(name = "MATK")
	private TaiKhoan taiKhoan;

	public NhanVien() {
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public Integer getMANV() {
		return MANV;
	}

	public void setMANV(Integer mANV) {
		MANV = mANV;
	}

	public String getHO() {
		return HO;
	}

	public void setHO(String hO) {
		HO = hO;
	}

	public String getTEN() {
		return TEN;
	}

	public void setTEN(String tEN) {
		TEN = tEN;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getDIACHI() {
		return DIACHI;
	}

	public void setDIACHI(String dIACHI) {
		DIACHI = dIACHI;
	}

	public Boolean getGIOITINH() {
		return GIOITINH;
	}

	public void setGIOITINH(Boolean gIOITINH) {
		GIOITINH = gIOITINH;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public Collection<NhanVien> getQuanLy() {
		return quanLy;
	}

	public void setQuanLy(Collection<NhanVien> quanLy) {
		this.quanLy = quanLy;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public String getNGSINH() {
		return NGSINH;
	}

	public void setNGSINH(String nGSINH) {
		NGSINH = nGSINH;
	}

	public Integer getLUONG() {
		return LUONG;
	}

	public void setLUONG(Integer lUONG) {
		LUONG = lUONG;
	}


	public Boolean getTRANGTHAI() {
		return TRANGTHAI;
	}

	public void setTRANGTHAI(Boolean tRANGTHAI) {
		TRANGTHAI = tRANGTHAI;
	}

	public Collection<PhieuNhap> getPhieuNhap() {
		return phieuNhap;
	}

	public void setPhieuNhap(Collection<PhieuNhap> phieuNhap) {
		this.phieuNhap = phieuNhap;
	}

	public Collection<HoaDon> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(Collection<HoaDon> hoaDon) {
		this.hoaDon = hoaDon;
	}


	@Override
	public String toString() {
		return "NhanVien [MANV=" + MANV + ", HO=" + HO + ", TEN=" + TEN + ", SDT=" + SDT + ", DIACHI=" + DIACHI
				+ ", GIOITINH=" + GIOITINH + ", EMAIL=" + EMAIL + ", CMND=" + CMND + ", NGSINH=" + NGSINH + ", LUONG="
				+ LUONG + ", TRANGTHAI=" + TRANGTHAI + ", phieuNhap=" + phieuNhap  + ", hoaDon="
				+ hoaDon +  ", taiKhoan=" + taiKhoan + "]";
	}
	
}
