declare namespace xf = "http://tempuri.org/beafunctions";
declare function xf:print($str as xs:string)
  as xs:string {
	 fn-bea:date-to-string-with-format("yyyy-MM-dd", xs:date("2005-07-15")) 

};
declare variable $str as xs:string external;
xf:print($str)

