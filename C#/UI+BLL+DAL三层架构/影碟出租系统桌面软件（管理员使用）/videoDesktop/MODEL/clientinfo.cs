//------------------------------------------------------------------------------
// <auto-generated>
//    此代码是根据模板生成的。
//
//    手动更改此文件可能会导致应用程序中发生异常行为。
//    如果重新生成代码，则将覆盖对此文件的手动更改。
// </auto-generated>
//------------------------------------------------------------------------------

namespace MODEL
{
    using System;
    using System.Collections.Generic;
    
    public partial class clientinfo
    {
        public clientinfo()
        {
            this.clientlogin = new HashSet<clientlogin>();
            this.hireinfo = new HashSet<hireinfo>();
        }
    
        public int id { get; set; }
        public string clientid { get; set; }
        public string clientname { get; set; }
        public string telphone { get; set; }
        public string email { get; set; }
    
        public virtual ICollection<clientlogin> clientlogin { get; set; }
        public virtual ICollection<hireinfo> hireinfo { get; set; }
    }
}
