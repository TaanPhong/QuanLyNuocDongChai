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
@Table(name = "DonDH")
public class DonDH {
	@Id
	@GeneratedValue
	private Integer MADDH;
	private String NGAYDAT;
	private String HONGUOINHAN;
	private String TENNGUOINHAN;
	private String DIACHINHAN;
	private String SDTNGUOINHAN;

	@ManyToOne
	@JoinColumn(name = "MAKH")
	private KhachHang khachHang;
	@OneToMany(mappedBy = "donDH", fetch = FetchType.EAGER)
	private Collection<CTDDH> ctddh;
	public Integer getMADDH() {
		return MADDH;
	}
	public void setMADDH(Integer mADDH) {
		MADDH = mADDH;
	}
	public String getHONGUOINHAN() {
		return HONGUOINHAN;
	}
	public void setHONGUOINHAN(String hONGUOINHAN) {
		HONGUOINHAN = hONGUOINHAN;
	}
	public String getTENNGUOINHAN() {
		return TENNGUOINHAN;
	}
	public void setTENNGUOINHAN(String tENNGUOINHAN) {
		TENNGUOINHAN = tENNGUOINHAN;
	}
	public String getDIACHINHAN() {
		return DIACHINHAN;
	}
	public void setDIACHINHAN(String dIACHINHAN) {
		DIACHINHAN = dIACHINHAN;
	}
	public String getSDTNGUOINHAN() {
		return SDTNGUOINHAN;
	}
	public void setSDTNGUOINHAN(String sDTNGUOINHAN) {
		SDTNGUOINHAN = sDTNGUOINHAN;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public Collection<CTDDH> getCtddh() {
		return ctddh;
	}
	public void setCtddh(Collection<CTDDH> ctddh) {
		this.ctddh = ctddh;
	}
	public String getNGAYDAT() {
		return NGAYDAT;
	}
	public void setNGAYDAT(String nGAYDAT) {
		NGAYDAT = nGAYDAT;
	}

}
