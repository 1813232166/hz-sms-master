/**
 * 
 */
package com.hzdjr.hzwd.common.supcan.freeform;

import com.hzdjr.hzwd.common.supcan.common.Common;
import com.hzdjr.hzwd.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Administrator
 * @version 2013-11-04
 */
@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}
	
	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}
	
}
