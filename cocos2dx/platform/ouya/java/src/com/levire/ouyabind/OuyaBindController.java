/****************************************************************************
 Created by Levire UG (haftungsbeschraenkt) & Co. KG , http://www.levire.com
 
 This program is free software. It comes without any warranty, to
 the extent permitted by applicable law. You can redistribute it
 and/or modify it under the terms of the Do What The Fuck You Want
 To Public License, Version 2, as published by Sam Hocevar. See
 http://www.wtfpl.net/ for more details.
 
 ****************************************************************************/

package com.levire.ouyabind;

import tv.ouya.console.api.OuyaController;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;

public class OuyaBindController
{	
	public static void init(final Activity activity) {
        Log.d("OUYA Controller", "init called");
        OuyaController.init(activity);
    }

	public static OuyaController getControllerByPlayer(int playerNum) {
		OuyaController ouyaController = OuyaController.getControllerByPlayer(playerNum);
		return ouyaController;
	}
	
	public static OuyaController getControllerByDeviceId(int deviceId)
	{
		OuyaController controller = OuyaController.getControllerByDeviceId(deviceId);
		return controller;
	}
	
	public static native void onNativeKeyDown(final int pKeyCode, final int deviceId);
	public static boolean onKeyDown(final int pKeyCode, final KeyEvent pKeyEvent)
	{
		boolean handled = OuyaController.onKeyDown(pKeyCode, pKeyEvent);
		OuyaBindController.onNativeKeyDown(pKeyCode, pKeyEvent.getDeviceId());
		return handled;
	}

	public static boolean onKeyDownByDeviceId(final int pKeyCode, final int deviceId)
	{
		OuyaBindController.onNativeKeyDown(pKeyCode, deviceId);
        return true;
	}
	
	public static native void onNativeKeyUp(final int pKeyCode, final int deviceId);
	public static boolean onKeyUp(final int pKeyCode, final KeyEvent pKeyEvent)
	{
		boolean handled = OuyaController.onKeyUp(pKeyCode, pKeyEvent);
		OuyaBindController.onNativeKeyUp(pKeyCode, pKeyEvent.getDeviceId());
		return handled;
	}
	public static boolean onKeyUpByDeviceId(final int pKeyCode, final int deviceId)
	{
		OuyaBindController.onNativeKeyUp(pKeyCode, deviceId);
        return true;
	}
	
	public static native void onNativeLeftStickMotionEvent(final int deviceId, final float axisXValue, final float axisYValue);
	public static native void onNativeRightStickMotionEvent(final int deviceId, final float axisXValue, final float axisYValue);
	
	public static boolean onGenericMotionEvent(android.view.MotionEvent event)
	{
		boolean handled = OuyaController.onGenericMotionEvent(event);
		OuyaController controller = OuyaController.getControllerByDeviceId(event.getDeviceId());
		
		OuyaBindController.onNativeLeftStickMotionEvent(event.getDeviceId(), controller.getAxisValue(OuyaController.AXIS_LS_X), controller.getAxisValue(OuyaController.AXIS_LS_Y));
		OuyaBindController.onNativeRightStickMotionEvent(event.getDeviceId(), controller.getAxisValue(OuyaController.AXIS_RS_X), controller.getAxisValue(OuyaController.AXIS_RS_Y));
		return handled;
	}
}
