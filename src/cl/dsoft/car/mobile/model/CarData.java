package cl.dsoft.car.mobile.model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

import cl.dsoft.car.mobile.db.Autenticacion;
import cl.dsoft.car.mobile.db.CambioRevision;
import cl.dsoft.car.mobile.db.CargaCombustible;
import cl.dsoft.car.mobile.db.CiaSeguros;
import cl.dsoft.car.mobile.db.Comuna;
import cl.dsoft.car.mobile.db.Log;
import cl.dsoft.car.mobile.db.MantencionBaseHecha;
import cl.dsoft.car.mobile.db.MantencionUsuario;
import cl.dsoft.car.mobile.db.MantencionUsuarioHecha;
import cl.dsoft.car.mobile.db.Pais;
import cl.dsoft.car.mobile.db.Recordatorio;
import cl.dsoft.car.mobile.db.CargaCombustible;
import cl.dsoft.car.mobile.db.Region;
import cl.dsoft.car.mobile.db.Reparacion;
import cl.dsoft.car.mobile.db.SeguroVehiculo;
import cl.dsoft.car.mobile.db.Usuario;
import cl.dsoft.car.mobile.db.Vehiculo;
import cl.dsoft.car.mobile.db.MantencionBase;
import cl.dsoft.car.mobile.db.MantencionPospuesta;
import cl.dsoft.car.mobile.db.VwCampaniaUsuario;
import cl.dsoft.car.mobile.db.Parametro;

@Root(name = "CarData")
@Order(elements={"paises", "regiones", "comunas", "usuarios", "autenticaciones", "vehiculos", "mantencionBases", "cambioRevisions",
		"mantencionBaseHechas", "mantencionUsuarios", "mantencionUsuarioHechas", "recordatorios", "cargaCombustibles", 
		"reparaciones", "ciaSeguross", "seguroVehiculos", "logs", "mantencionPospuestas", "vwCampaniaUsuarios", "parametros"})
//If you want you can define the order in which the fields are written
//Optional
//@Order(elements = { "usuarios", "vehiculos", "mantencionUsuarios", "mantencionUsuarioHechas", "recordatorios", "rendimientos", "reparaciones" })
public class CarData {

	@ElementList(required=false)
	protected ArrayList<Pais> paises;
	@ElementList(required=false)
	protected ArrayList<Region> regiones;
	@ElementList(required=false)
	protected ArrayList<Comuna> comunas;
	@ElementList(required=false)
	protected ArrayList<Usuario> usuarios;
	@ElementList(required=false)
	protected ArrayList<Autenticacion> autenticaciones;
	@ElementList(required=false)
	protected ArrayList<Vehiculo> vehiculos;
	@ElementList(required=false)
	protected ArrayList<MantencionBase> mantencionBases;
	@ElementList(required=false)
	protected ArrayList<CambioRevision> cambioRevisions;
	@ElementList(required=false)
	protected ArrayList<MantencionBaseHecha> mantencionBaseHechas;
	@ElementList(required=false)
	protected ArrayList<MantencionUsuario> mantencionUsuarios;
	@ElementList(required=false)
	protected ArrayList<MantencionUsuarioHecha> mantencionUsuarioHechas;
	@ElementList(required=false)
	protected ArrayList<Recordatorio> recordatorios;
	@ElementList(required=false)
	protected ArrayList<CargaCombustible> cargaCombustibles;
	@ElementList(required=false)
	protected ArrayList<Reparacion> reparaciones;
	@ElementList(required=false)
	protected ArrayList<CiaSeguros> ciaSeguross;
	@ElementList(required=false)
	protected ArrayList<SeguroVehiculo> seguroVehiculos;
	@ElementList(required=false)
	protected ArrayList<Log> logs;
	@ElementList(required=false)
	protected ArrayList<MantencionPospuesta> mantencionPospuestas;
	@ElementList(required=false)
	protected ArrayList<VwCampaniaUsuario> vwCampaniaUsuarios;
	@ElementList(required=false)
	protected ArrayList<Parametro> parametros;
	
	@Attribute(required=false)
	protected long idUsuario;
	@Attribute(required=false)
	protected String fechaSincroAnterior;
	@Attribute(required=false)
	protected String fechaActual;
	
	public CarData() {

		this.paises = null;
		this.regiones = null;
		this.comunas = null;
		this.usuarios = null;
		this.autenticaciones = null;
		this.vehiculos = null;
		this.mantencionBases = null;
		this.cambioRevisions = null;
		this.mantencionBaseHechas = null;
		this.mantencionUsuarios = null;
		this.mantencionUsuarioHechas = null;
		this.recordatorios = null;
		this.cargaCombustibles = null;
		this.reparaciones = null;
		this.ciaSeguross = null;
		this.seguroVehiculos = null;
		this.logs = null;
		this.mantencionPospuestas = null;
		this.vwCampaniaUsuarios = null;
		this.parametros = null;
	}	

	/*
	 * generacion de datos de App Movil a Servidor
	 */
	public CarData(java.sql.Connection conn, Long idUsuario, String fechaModificacion) {

    	ArrayList<AbstractMap.SimpleEntry<String, String>> listParameters;
    	
    	try {
			listParameters = new ArrayList<AbstractMap.SimpleEntry<String, String>>();
			
			listParameters.add(new AbstractMap.SimpleEntry<String, String>("id_usuario", String.valueOf(idUsuario)));
			listParameters.add(new AbstractMap.SimpleEntry<String, String>("mas reciente", fechaModificacion));
			
			//this.paises = Pais.seek(conn, listParameters, null, null, 0, 10000);
			
			//this.regiones = Region.seek(conn, listParameters, null, null, 0, 10000);
			
			//this.comunas = Comuna.seek(conn, listParameters, null, null, 0, 10000);
			
			this.mantencionBaseHechas = MantencionBaseHecha.seek(conn, listParameters, null, null, 0, 10000);
			
			this.mantencionUsuarios = MantencionUsuario.seek(conn, listParameters, null, null, 0, 10000);
			
			this.mantencionUsuarioHechas = MantencionUsuarioHecha.seek(conn, listParameters, null, null, 0, 10000);
			
			this.recordatorios = Recordatorio.seek(conn, listParameters, null, null, 0, 10000);
			
			this.cargaCombustibles = CargaCombustible.seek(conn, listParameters, null, null, 0, 10000);
			
			this.reparaciones = Reparacion.seek(conn, listParameters, null, null, 0, 10000);
			
			this.usuarios = Usuario.seek(conn, listParameters, null, null, 0, 10000);
			
			this.autenticaciones = Autenticacion.seek(conn, listParameters, null, null, 0, 10000);
			
			this.vehiculos = Vehiculo.seek(conn, listParameters, null, null, 0, 10000);
			
			//this.ciaSeguross = CiaSeguros.seek(conn, listParameters, null, null, 0, 10000);
			
			this.seguroVehiculos = SeguroVehiculo.seek(conn, listParameters, null, null, 0, 10000);
			
			this.logs = Log.seek(conn, listParameters, null, null, 0, 10000);
			
			this.mantencionPospuestas = MantencionPospuesta.seek(conn, listParameters, null, null, 0, 10000);
			
			//this.notificaciones = Notificacion.seek(conn, listParameters, null, null, 0, 10000);
			
			//this.parametros = Parametro.seek(conn, listParameters, null, null, 0, 10000);
			
			// atributos
			this.idUsuario = idUsuario;
			this.fechaSincroAnterior = fechaModificacion;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.fechaActual = formatter.format(new Date());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * @return the mantencionBases
	 */
	public ArrayList<MantencionBase> getMantencionBases() {
		return mantencionBases;
	}

	/**
	 * @param mantencionBases the mantencionBases to set
	 */
	public void setMantencionBases(ArrayList<MantencionBase> mantencionBases) {
		this.mantencionBases = mantencionBases;
	}

	/**
	 * @return the cambioRevisions
	 */
	public ArrayList<CambioRevision> getCambioRevisions() {
		return cambioRevisions;
	}

	/**
	 * @param cambioRevisions the cambioRevisions to set
	 */
	public void setCambioRevisions(ArrayList<CambioRevision> cambioRevisions) {
		this.cambioRevisions = cambioRevisions;
	}

	/**
	 * @return the mantencionPospuestas
	 */
	public ArrayList<MantencionPospuesta> getMantencionPospuestas() {
		return mantencionPospuestas;
	}

	/**
	 * @param mantencionPospuestas the mantencionPospuestas to set
	 */
	public void setMantencionPospuestas(
			ArrayList<MantencionPospuesta> mantencionPospuestas) {
		this.mantencionPospuestas = mantencionPospuestas;
	}

	/**
	 * @return the parametros
	 */
	public ArrayList<Parametro> getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(ArrayList<Parametro> parametros) {
		this.parametros = parametros;
	}

	/**
	 * @return the ciaSeguross
	 */
	public ArrayList<CiaSeguros> getCiaSeguross() {
		return ciaSeguross;
	}

	/**
	 * @param ciaSeguross the ciaSeguross to set
	 */
	public void setCiaSeguross(ArrayList<CiaSeguros> ciaSeguross) {
		this.ciaSeguross = ciaSeguross;
	}

	/**
	 * @return the seguroVehiculos
	 */
	public ArrayList<SeguroVehiculo> getSeguroVehiculos() {
		return seguroVehiculos;
	}

	/**
	 * @param seguroVehiculos the seguroVehiculos to set
	 */
	public void setSeguroVehiculos(ArrayList<SeguroVehiculo> seguroVehiculos) {
		this.seguroVehiculos = seguroVehiculos;
	}

	/**
	 * @return the logs
	 */
	public ArrayList<Log> getLogs() {
		return logs;
	}

	/**
	 * @param logs the logs to set
	 */
	public void setLogs(ArrayList<Log> logs) {
		this.logs = logs;
	}

	/**
	 * @return the paises
	 */
	public ArrayList<Pais> getPaises() {
		return paises;
	}

	/**
	 * @param paises the paises to set
	 */
	public void setPaises(ArrayList<Pais> paises) {
		this.paises = paises;
	}

	/**
	 * @return the regiones
	 */
	public ArrayList<Region> getRegiones() {
		return regiones;
	}

	/**
	 * @param regiones the regiones to set
	 */
	public void setRegiones(ArrayList<Region> regiones) {
		this.regiones = regiones;
	}

	/**
	 * @return the comunas
	 */
	public ArrayList<Comuna> getComunas() {
		return comunas;
	}

	/**
	 * @param comunas the comunas to set
	 */
	public void setComunas(ArrayList<Comuna> comunas) {
		this.comunas = comunas;
	}

	/**
	 * @return the usuarios
	 */
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * @return the autenticaciones
	 */
	public ArrayList<Autenticacion> getAutenticaciones() {
		return autenticaciones;
	}

	/**
	 * @param autenticaciones the autenticaciones to set
	 */
	public void setAutenticaciones(ArrayList<Autenticacion> autenticaciones) {
		this.autenticaciones = autenticaciones;
	}

	/**
	 * @return the vehiculos
	 */
	public ArrayList<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	/**
	 * @param vehiculos the vehiculos to set
	 */
	public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	/**
	 * @return the mantencionBaseHechas
	 */
	public ArrayList<MantencionBaseHecha> getMantencionBaseHechas() {
		return mantencionBaseHechas;
	}

	/**
	 * @param mantencionBaseHechas the mantencionBaseHechas to set
	 */
	public void setMantencionBaseHechas(
			ArrayList<MantencionBaseHecha> mantencionBaseHechas) {
		this.mantencionBaseHechas = mantencionBaseHechas;
	}

	/**
	 * @return the mantencionUsuarios
	 */
	public ArrayList<MantencionUsuario> getMantencionUsuarios() {
		return mantencionUsuarios;
	}

	/**
	 * @param mantencionUsuarios the mantencionUsuarios to set
	 */
	public void setMantencionUsuarios(ArrayList<MantencionUsuario> mantencionUsuarios) {
		this.mantencionUsuarios = mantencionUsuarios;
	}

	/**
	 * @return the mantencionUsuarioHechas
	 */
	public ArrayList<MantencionUsuarioHecha> getMantencionUsuarioHechas() {
		return mantencionUsuarioHechas;
	}

	/**
	 * @param mantencionUsuarioHechas the mantencionUsuarioHechas to set
	 */
	public void setMantencionUsuarioHechas(
			ArrayList<MantencionUsuarioHecha> mantencionUsuarioHechas) {
		this.mantencionUsuarioHechas = mantencionUsuarioHechas;
	}

	/**
	 * @return the recordatorios
	 */
	public ArrayList<Recordatorio> getRecordatorios() {
		return recordatorios;
	}

	/**
	 * @param recordatorios the recordatorios to set
	 */
	public void setRecordatorios(ArrayList<Recordatorio> recordatorios) {
		this.recordatorios = recordatorios;
	}

	/**
	 * @return the rendimientos
	 */
	public ArrayList<CargaCombustible> getCargaCombustibles() {
		return cargaCombustibles;
	}

	/**
	 * @param rendimientos the rendimientos to set
	 */
	public void setCargaCombustibles(ArrayList<CargaCombustible> cargaCombustibles) {
		this.cargaCombustibles = cargaCombustibles;
	}

	/**
	 * @return the reparaciones
	 */
	public ArrayList<Reparacion> getReparaciones() {
		return reparaciones;
	}

	/**
	 * @param reparaciones the reparaciones to set
	 */
	public void setReparaciones(ArrayList<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}
	
	/**
	 * @return the mantencionPropuestas
	 */
	public ArrayList<MantencionPospuesta> getMantencionPropuestas() {
		return mantencionPospuestas;
	}

	/**
	 * @param mantencionPropuestas the mantencionPropuestas to set
	 */
	public void setMantencionPropuestas(
			ArrayList<MantencionPospuesta> mantencionPropuestas) {
		this.mantencionPospuestas = mantencionPropuestas;
	}

	/**
	 * @return the vwCampaniaUsuarios
	 */
	public ArrayList<VwCampaniaUsuario> getVwCampaniaUsuarios() {
		return vwCampaniaUsuarios;
	}

	/**
	 * @param vwCampaniaUsuarios the vwCampaniaUsuarios to set
	 */
	public void setVwCampaniaUsuarios(ArrayList<VwCampaniaUsuario> vwCampaniaUsuarios) {
		this.vwCampaniaUsuarios = vwCampaniaUsuarios;
	}

	/**
	 * @return the idUsuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the fechaSincroAnterior
	 */
	public String getFechaSincroAnterior() {
		return fechaSincroAnterior;
	}

	/**
	 * @param fechaSincroAnterior the fechaSincroAnterior to set
	 */
	public void setFechaSincroAnterior(String fechaSincroAnterior) {
		this.fechaSincroAnterior = fechaSincroAnterior;
	}

	/**
	 * @return the fechaActual
	 */
	public String getFechaActual() {
		return fechaActual;
	}

	/**
	 * @param fechaActual the fechaActual to set
	 */
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

    /**
     * @param _fechaNacimiento the _fechaNacimiento to set as java.util.Date
     */
    public void setFechaActual(Date fechaActual) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.fechaActual = formatter.format(fechaActual);
;
    }

    /*
	 * se graban en la bd de la App Movil los datos que llegan desde el Servidor
	 */
	public void save(java.sql.Connection conn) throws SQLException {
		
		if (this.getPaises() != null) {
			for (Pais pais : this.getPaises()) {
				
				pais.save(conn);
			}
		}
		if (this.getRegiones() != null) {
			for (Region region : this.getRegiones()) {
				
				region.save(conn);
			}
		}
		if (this.getComunas() != null) {
			for (Comuna comuna : this.getComunas()) {
				
				comuna.save(conn);
			}
		}
		if (this.getUsuarios() != null) {
			for (Usuario usuario : this.getUsuarios()) {
				
				usuario.save(conn);
			}
		}
		if (this.getAutenticaciones() != null) {
			for (Autenticacion autenticacion : this.getAutenticaciones()) {
				
				autenticacion.save(conn);
			}
		}
		if (this.getVehiculos() != null) {
			for (Vehiculo vehiculo : this.getVehiculos()) {
				
				vehiculo.save(conn);
			}
		}
		if (this.getMantencionBases() != null) {
			for (MantencionBase mantencionBase : this.getMantencionBases()) {
				
				mantencionBase.save(conn);
			}
		}
		if (this.getCambioRevisions() != null) {
			for (CambioRevision cambioRevision : this.getCambioRevisions()) {
				
				cambioRevision.save(conn);
			}
		}
		if (this.getMantencionBaseHechas() != null) {
			for (MantencionBaseHecha mantencionBaseHecha : this.getMantencionBaseHechas()) {
				
				mantencionBaseHecha.save(conn);
			}
		}
		if (this.getMantencionUsuarios() != null) {
			for (MantencionUsuario mantencionUsuario : this.getMantencionUsuarios()) {
				
				mantencionUsuario.save(conn);
			}
		}
		if (this.getMantencionUsuarioHechas() != null) {
			for (MantencionUsuarioHecha mantencionUsuarioHecha : this.getMantencionUsuarioHechas()) {
				
				mantencionUsuarioHecha.save(conn);
			}
		}
		if (this.getRecordatorios() != null) {
			for (Recordatorio recordatorio : this.getRecordatorios()) {
				
				recordatorio.save(conn);
			}
		}
		if (this.getCargaCombustibles() != null) {
			for (CargaCombustible rendimiento : this.getCargaCombustibles()) {
				
				rendimiento.save(conn);
			}
		}
		if (this.getReparaciones() != null) {
			for (Reparacion reparacion : this.getReparaciones()) {
				
				reparacion.save(conn);
			}
		}
		if (this.getCiaSeguross() != null) {
			for (CiaSeguros ciaSeguros : this.getCiaSeguross()) {
				
				ciaSeguros.save(conn);
			}
		}
		if (this.getSeguroVehiculos() != null) {
			for (SeguroVehiculo seguroVehiculo : this.getSeguroVehiculos()) {
				
				seguroVehiculo.save(conn);
			}
		}
		/*
		if (this.getLogs() != null) {
			for (Log log : this.getLogs()) {
				
				log.save(conn);
			}
		}
		*/
		if (this.getMantencionPropuestas() != null) {
			for (MantencionPospuesta mantencionPospuesta : this.getMantencionPropuestas()) {
				
				mantencionPospuesta.save(conn);
			}
		}
		if (this.getVwCampaniaUsuarios() != null) {
			for (VwCampaniaUsuario vwCampaniaUsuario : this.getVwCampaniaUsuarios()) {
				
				vwCampaniaUsuario.save(conn);
			}
		}
		if (this.getParametros() != null) {
			for (Parametro parametro : this.getParametros()) {
				
				parametro.save(conn);
			}
		}
	}
	
	public boolean hasData() {
		boolean ret = false;
		
		if (this.getPaises() != null) {
			if (!this.getPaises().isEmpty()) {
				return true;
			}
		}
		if (this.getRegiones() != null) {
			if (!this.getRegiones().isEmpty()) {
				return true;
			}
		}
		if (this.getComunas() != null) {
			if (!this.getComunas().isEmpty()) {
				return true;
			}
		}
		if (this.getUsuarios() != null) {
			if (!this.getUsuarios().isEmpty()) {
				return true;
			}
		}
		if (this.getAutenticaciones() != null) {
			if (!this.getAutenticaciones().isEmpty()) {
				return true;
			}
		}
		if (this.getVehiculos() != null) {
			if (!this.getVehiculos().isEmpty()) {
				return true;
			}
		}
		if (this.getMantencionBases() != null) {
			if (!this.getMantencionBases().isEmpty()) {
				return true;
			}
		}
		if (this.getCambioRevisions() != null) {
			if (!this.getCambioRevisions().isEmpty()) {
				return true;
			}
		}
		if (this.getMantencionBaseHechas() != null) {
			if (!this.getMantencionBaseHechas().isEmpty()) {
				return true;
			}
		}
		if (this.getMantencionUsuarios() != null) {
			if (!this.getMantencionUsuarios().isEmpty()) {
				return true;
			}
		}
		if (this.getMantencionUsuarioHechas() != null) {
			if (!this.getMantencionUsuarioHechas().isEmpty()) {
				return true;
			}
		}
		if (this.getRecordatorios() != null) {
			if (!this.getRecordatorios().isEmpty()) {
				return true;
			}
		}
		if (this.getCargaCombustibles() != null) {
			if (!this.getCargaCombustibles().isEmpty()) {
				return true;
			}
		}
		if (this.getReparaciones() != null) {
			if (!this.getReparaciones().isEmpty()) {
				return true;
			}
		}
		if (this.getCiaSeguross() != null) {
			if (!this.getCiaSeguross().isEmpty()) {
				return true;
			}
		}
		if (this.getSeguroVehiculos() != null) {
			if (!this.getSeguroVehiculos().isEmpty()) {
				return true;
			}
		}
		if (this.getLogs() != null) {
			if (!this.getLogs().isEmpty()) {
				return true;
			}
		}
		if (this.getMantencionPropuestas() != null) {
			if (!this.getMantencionPropuestas().isEmpty()) {
				return true;
			}
		}
		if (this.getVwCampaniaUsuarios() != null) {
			if (!this.getVwCampaniaUsuarios().isEmpty()) {
				return true;
			}
		}
		if (this.getParametros() != null) {
			if (!this.getParametros().isEmpty()) {
				return true;
			}
		}

		return ret;
	}
}
