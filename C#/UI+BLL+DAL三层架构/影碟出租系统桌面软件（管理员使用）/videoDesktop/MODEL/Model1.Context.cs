﻿//------------------------------------------------------------------------------
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
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class videoEntities : DbContext
    {
        public videoEntities()
            : base("name=videoEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public DbSet<admininfo> admininfo { get; set; }
        public DbSet<adminlogin> adminlogin { get; set; }
        public DbSet<clientinfo> clientinfo { get; set; }
        public DbSet<clientlogin> clientlogin { get; set; }
        public DbSet<contact> contact { get; set; }
        public DbSet<hireinfo> hireinfo { get; set; }
        public DbSet<recommend> recommend { get; set; }
        public DbSet<review> review { get; set; }
        public DbSet<sysdiagrams> sysdiagrams { get; set; }
        public DbSet<videoinfo> videoinfo { get; set; }
    }
}
