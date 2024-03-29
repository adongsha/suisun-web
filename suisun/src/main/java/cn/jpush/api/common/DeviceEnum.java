package cn.jpush.api.common;

public enum DeviceEnum {
    
	Android("android"),
	
	IOS("ios");
	
	private final String value;
	
	private DeviceEnum(final String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
}
