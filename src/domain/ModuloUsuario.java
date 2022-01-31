package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="modulo_usuario")
public class ModuloUsuario {

	
		@Id
		@Column(name="ID")
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Integer id;
		
		@Column(name="EMPRESA_ID")
		private Integer empresa_id;
		
		@Column(name="MODULO_ID")		
		private Integer modulo_id;
		
		@Column(name="USUARIO_ID")
		private Integer usuario_id;
		
		public ModuloUsuario(){
			
		}

		public ModuloUsuario(Integer id, Integer empresa_id, Integer modulo_id,
				Integer usuario_id) {
			super();
			this.id = id;
			this.empresa_id = empresa_id;
			this.modulo_id = modulo_id;
			this.usuario_id = usuario_id;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getEmpresa_id() {
			return empresa_id;
		}

		public void setEmpresa_id(Integer empresa_id) {
			this.empresa_id = empresa_id;
		}

		public Integer getModulo_id() {
			return modulo_id;
		}

		public void setModulo_id(Integer modulo_id) {
			this.modulo_id = modulo_id;
		}

		public Integer getUsuario_id() {
			return usuario_id;
		}

		public void setUsuario_id(Integer usuario_id) {
			this.usuario_id = usuario_id;
		}
		
		
}
