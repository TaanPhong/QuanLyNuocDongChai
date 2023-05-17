package ptithcm.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CTHD")
public class CTHD implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "MAHD")
	private HoaDon hoaDon;

	@Id
	@ManyToOne
	@JoinColumn(name = "MASP")
	private SanPham sanPham;
	private Integer DONGIA;
	private Integer SL;

	
	
	public CTHD() {
//		super();
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
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
