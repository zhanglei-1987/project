Set objWMIService = GetObject("winmgmts:")
Set colItems = objWMIService.InstancesOf("Win32_Processor")
For Each objItem in colItems 
Wscript.Echo objItem.ProcessorId 
Next