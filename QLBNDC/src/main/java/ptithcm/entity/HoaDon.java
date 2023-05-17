package ptithcm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HOADON")
public class HoaDon {
	@Id
	@GeneratedValue
	private Integer MAHD;
	
	private String NGAYLAPHD;

	@ManyToOne
	@JoinColumn(name = "MANV")
	private NhanVien nhanVien;

	public Integer getMAHD() {
		return MAHD;
	}

	public void setMAHD(Integer mAHD) {
		MAHD = mAHD;
	}

	

	public String getNGAYLAPHD() {
		return NGAYLAPHD;
	}

	public void setNGAYLAPHD(String nGAYLAPHD) {
		NGAYLAPHD = nGAYLAPHD;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public HoaDon() {
//		super();
	}

	@Override
	public String toString() {
		return "HoaDon [MAHD=" + MAHD + ", NGAYLAPHD=" + NGAYLAPHD + ", nhanVien=" + nhanVien + "]";
	}
	

}
