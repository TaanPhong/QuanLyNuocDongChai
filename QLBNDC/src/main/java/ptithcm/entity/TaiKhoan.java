package ptithcm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan {
	@Id
	@GeneratedValue
	private Integer MATK;
	private String MK;
	private String TenDN;
	private Integer DOI;

	@ManyToOne
	@JoinColumn(name = "MAPQ")
	private PhanQuyen phanQuyen;

	public TaiKhoan() {
	}

	public Integer getMATK() {
		return MATK;
	}

	public void setMATK(Integer mATK) {
		MATK = mATK;
	}

	public String getMK() {
		return MK;
	}

	public void setMK(String mK) {
		MK = mK;
	}

	public PhanQuyen getPhanQuyen() {
		return phanQuyen;
	}

	public void setPhanQuyen(PhanQuyen phanQuyen) {
		this.phanQuyen = phanQuyen;
	}

	public String getTenDN() {
		return TenDN;
	}

	public void setTenDN(String tenDN) {
		TenDN = tenDN;
	}

	public Integer getDOI() {
		return DOI;
	}

	public void setDOI(Integer dOI) {
		DOI = dOI;
	}

	@Override
	public String toString() {
		return "TaiKhoan [MATK=" + MATK + ", MK=" + MK + ", TenDN=" + TenDN + ", phanQuyen=" + phanQuyen + "]";
	}

}
