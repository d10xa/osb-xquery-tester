declare namespace xf = "http://tempuri.org/beafunctions";
declare function xf:print()
  as xs:string {
	 fn-bea:date-to-string-with-format("yyyy-MM-dd", xs:date("2005-07-15")) 

};
xf:print()

