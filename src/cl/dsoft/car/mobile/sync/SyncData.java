package cl.dsoft.car.mobile.sync;

import cl.dsoft.car.mobile.model.CarData;

public class SyncData {

	private String strResponse;
	private CarData carData;

	public SyncData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the strResponse
	 */
	public String getStrResponse() {
		return strResponse;
	}

	/**
	 * @param strResponse the strResponse to set
	 */
	public void setStrResponse(String strResponse) {
		this.strResponse = strResponse;
	}

	/**
	 * @return the carData
	 */
	public CarData getCarData() {
		return carData;
	}

	/**
	 * @param carData the carData to set
	 */
	public void setCarData(CarData carData) {
		this.carData = carData;
	}

}
