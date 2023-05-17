package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "KHACHHANG")
public class KhachHang {
	@Id
	@GeneratedValue
	private Integer MAKH;
	private String HO;
	private String TEN;
	private String DIACHI;
	private String SDT;
	private String NGSINH;
	private String EMAIL;

	@OneToMany(mappedBy = "khachHang", fetch = FetchType.EAGER)
	private Collection<DonDH> donDH;

	@OneToOne
	@JoinColumn(name = "MATK")
	private TaiKhoan taiKhoan;

	
	public KhachHang() {
	}

	@Override
	public String toString() {
		return "KhachHang [MAKH=" + MAKH + ", HO=" + HO + ", TEN=" + TEN + ", DIACHI=" + DIACHI + ", SDT=" + SDT
				+ ", NGSINH=" + NGSINH + ", EMAIL=" + EMAIL + ", donDH="
				+ donDH + ", taiKhoan=" + taiKhoan + "]";
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public Integer getMAKH() {
		return MAKH;
	}

	public void setMAKH(Integer mAKH) {
		MAKH = mAKH;
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

	public String getDIACHI() {
		return DIACHI;
	}

	public void setDIACHI(String dIACHI) {
		DIACHI = dIACHI;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getNGSINH() {
		return NGSINH;
	}

	public void setNGSINH(String nGSINH) {
		NGSINH = nGSINH;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public Collection<DonDH> getDonDH() {
		return donDH;
	}

	public void setDonDH(Collection<DonDH> donDH) {
		this.donDH = donDH;
	}

}
