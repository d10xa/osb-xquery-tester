declare namespace xf = "http://tempuri.org/vijfhuizen/com/myMessage/";

declare function xf:strip-namespace($e as element()) as element()
{
	element { xs:QName(local-name($e)) }
	{
 	for $child in $e/(@* , node())
 		return
   			if ($child instance of element())
   			  then xf:strip-namespace($child)
   			  else $child
    }
};

declare variable $e as element() external;
xf:strip-namespace($e)
