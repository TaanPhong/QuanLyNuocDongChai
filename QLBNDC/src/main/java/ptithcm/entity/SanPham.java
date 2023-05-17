package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SANPHAM")
public class SanPham {
	@Id
	@GeneratedValue
	private Integer MASP;
	private Integer SLTON;
	private String MOTA;
	private String TENSP;
	private String HINHANH;
	private Integer GIA;

	@ManyToOne
	@JoinColumn(name = "MALOAI")
	private Loai loai;

	@ManyToOne
	@JoinColumn(name = "MAHANG")
	private Hang hang;

//	@OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
//	private Collection<CTPT> ctpt;

	@OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
	private Collection<CTDDH> ctddh;

	@OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
	private Collection<CTHD> cthd;

	@OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
	private Collection<CTPN> ctpn;

	
	public SanPham() {
		super();
	}

	
	public Integer getGIA() {
		return GIA;
	}


	public void setGIA(Integer gIA) {
		GIA = gIA;
	}


	public Integer getMASP() {
		return MASP;
	}

	public void setMASP(Integer mASP) {
		MASP = mASP;
	}

	public Integer getSLTON() {
		return SLTON;
	}

	public void setSLTON(Integer sLTON) {
		SLTON = sLTON;
	}

	public String getMOTA() {
		return MOTA;
	}

	public void setMOTA(String mOTA) {
		MOTA = mOTA;
	}

	public String getTENSP() {
		return TENSP;
	}

	public void setTENSP(String tENSP) {
		TENSP = tENSP;
	}

	public String getHINHANH() {
		return HINHANH;
	}

	public void setHINHANH(String hINHANH) {
		HINHANH = hINHANH;
	}

	public Loai getLoai() {
		return loai;
	}

	public void setLoai(Loai loai) {
		this.loai = loai;
	}

	public Hang getHang() {
		return hang;
	}

	public void setHang(Hang hang) {
		this.hang = hang;
	}

	public Collection<CTDDH> getCtddh() {
		return ctddh;
	}

	public void setCtddh(Collection<CTDDH> ctddh) {
		this.ctddh = ctddh;
	}

	public Collection<CTHD> getCthd() {
		return cthd;
	}

	public void setCthd(Collection<CTHD> cthd) {
		this.cthd = cthd;
	}

	public Collection<CTPN> getCtpn() {
		return ctpn;
	}

	public void setCtpn(Collection<CTPN> ctpn) {
		this.ctpn = ctpn;
	}


	@Override
	public String toString() {
		return "SanPham [MASP=" + MASP + ", SLTON=" + SLTON + ", MOTA=" + MOTA + ", TENSP=" + TENSP + ", HINHANH="
				+ HINHANH + ", GIA=" + GIA + ", loai=" + loai + ", hang=" + hang + ", ctddh=" + ctddh + ", cthd=" + cthd
				+ ", ctpn=" + ctpn + ",";
	}

}
