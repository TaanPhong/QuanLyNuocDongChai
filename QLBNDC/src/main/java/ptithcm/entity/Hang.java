package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HANG")
public class Hang {
	@Id
	@GeneratedValue
	private Integer MAHANG;
	private String TENHANG;
	
	
	@OneToMany(mappedBy = "hang", fetch = FetchType.EAGER)
	private Collection<SanPham> sanPham;

	public Integer getMAHANG() {
		return MAHANG;
	}

	public void setMAHANG(Integer mAHANG) {
		MAHANG = mAHANG;
	}

	public String getTENHANG() {
		return TENHANG;
	}

	public void setTENHANG(String tENHANG) {
		TENHANG = tENHANG;
	}

	public Collection<SanPham> getSanPham() {
		return sanPham;
	}

	public void setSanPham(Collection<SanPham> sanPham) {
		this.sanPham = sanPham;
	}
	
}
