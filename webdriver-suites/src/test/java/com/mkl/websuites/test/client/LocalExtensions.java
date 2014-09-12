package com.mkl.websuites.test.client;

import com.mkl.websuites.ext.Customization;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.services.ServiceDefinition.Service;
import com.mkl.websuites.test.client.ext.MyBrowserController;



@Customization(serviceOverrides = {
		
		@Service(
				service = BrowserController.class,
				implementation = MyBrowserController.class
		)
})
public class LocalExtensions {

}
