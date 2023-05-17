package ptithcm.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CTDDH")
public class CTDDH implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "MADDH")
	private DonDH donDH;

	@Id
	@ManyToOne
	@JoinColumn(name = "MASP")
	private SanPham sanPham;

	private Integer SL;
	private Integer DONGIA;

	public DonDH getDonDH() {
		return donDH;
	}

	public void setDonDH(DonDH donDH) {
		this.donDH = donDH;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public Integer getSL() {
		return SL;
	}

	public void setSL(Integer sL) {
		SL = sL;
	}

	public Integer getDONGIA() {
		return DONGIA;
	}

	public void setDONGIA(Integer dONGIA) {
		DONGIA = dONGIA;
	}

}
