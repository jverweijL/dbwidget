package com.liferay.demo.db.api;

import javax.portlet.RenderRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author jverweij
 */
public interface DbViewingService {

    public List<HashMap<String,Object>>getResult(RenderRequest request);
}