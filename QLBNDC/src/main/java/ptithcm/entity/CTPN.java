package ptithcm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CTPN")
public class CTPN implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "MASP")
	private SanPham sanPham;

	@Id
	@ManyToOne
	@JoinColumn(name = "MAPN")
	private PhieuNhap phieuNhap;

	private Integer SL;
	private Integer DONGIA;

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public PhieuNhap getPhieuNhap() {
		return phieuNhap;
	}

	public void setPhieuNhap(PhieuNhap phieuNhap) {
		this.phieuNhap = phieuNhap;
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

	public CTPN(SanPham sanPham, PhieuNhap phieuNhap, Integer sL, Integer dONGIA) {
		super();
		this.sanPham = sanPham;
		this.phieuNhap = phieuNhap;
		SL = sL;
		DONGIA = dONGIA;
	}

	public CTPN() {
		super();
	}

}
