package ptithcm.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Collection;

@Entity
@Table(name = "PHIEUNHAP")
public class PhieuNhap {
	@Id
	@GeneratedValue
	private Integer MAPN;
	private String NGAYNHAP;

	@ManyToOne
	@JoinColumn(name = "MANV")
	private NhanVien nhanVien;

	@OneToMany(mappedBy = "phieuNhap", fetch = FetchType.EAGER)
	private Collection<CTPN> ctpn;

	public PhieuNhap() {
		super();
	}

	public PhieuNhap(Integer mAPN, String nGAYNHAP, NhanVien nhanVien) {
		super();
		MAPN = mAPN;
		NGAYNHAP = nGAYNHAP;
		this.nhanVien = nhanVien;
	}

	public Collection<CTPN> getCtpn() {
		return ctpn;
	}

	public void setCtpn(Collection<CTPN> ctpn) {
		this.ctpn = ctpn;
	}

	public Integer getMAPN() {
		return MAPN;
	}

	public void setMAPN(Integer mAPN) {
		MAPN = mAPN;
	}

	public String getNGAYNHAP() {
		return NGAYNHAP;
	}

	public void setNGAYNHAP(String nGAYNHAP) {
		NGAYNHAP = nGAYNHAP;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

}
