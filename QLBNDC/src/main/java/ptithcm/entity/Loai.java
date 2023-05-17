package ptithcm.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LOAI")
public class Loai {
	@Id
	@GeneratedValue
	private Integer MALOAI;
	private String TENLOAI;

	@OneToMany(mappedBy = "loai", fetch = FetchType.EAGER)
	private Collection<SanPham> sanPham;
	
	public Integer getMALOAI() {
		return MALOAI;
	}

	public void setMALOAI(Integer mALOAI) {
		MALOAI = mALOAI;
	}

	public String getTENLOAI() {
		return TENLOAI;
	}

	public void setTENLOAI(String tENLOAI) {
		TENLOAI = tENLOAI;
	}

	public Collection<SanPham> getSanpham() {
		return sanPham;
	}

	public void setSanpham(Collection<SanPham> sanpham) {
		this.sanPham = sanpham;
	}

}
