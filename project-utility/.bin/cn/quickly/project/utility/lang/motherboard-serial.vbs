Set objWMIService = GetObject("winmgmts:")
Set colItems = objWMIService.InstancesOf("Win32_BaseBoard") 
For Each objItem in colItems 
Wscript.Echo objItem.SerialNumber 
Next