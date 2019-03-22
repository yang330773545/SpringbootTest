package com.vinzor.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable{

	/**
	 * 随机
	 */
	private static final long serialVersionUID = 840685157317540110L;
	/** @Entity
	 * name	String	实体名称，在 JPQL 中用于引用该实体类。默认为该类的简单类名称。
	 */
	/**
	 * @GeneratedValue用于标注主键的生成策略结合id使用
	 * 	参数strategy可选值
	 *	GenerationType.IDENTITY：采用数据库ID自增长的方式来自增主键字段，Oracle 不支持这种方式。
	 *	GenerationType.AUTO： JPA自动选择合适的策略，是默认选项。
	 *	GenerationType.SEQUENCE：通过序列产生主键，通过@SequenceGenerator 注解指定序列名，MySql不支持这种方式。
	 *	GenerationType.TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
	 *  参数generator String型
	 *  主键生成器的名称。该名称为 @TableGenerator 或 @SequenceGenerator 注解中 name 参数的值。默认为持久化提供者（如 Hibernate）提供的id生成器
	 */
	/**  @Column
	 *  name	           String	列的名称，默认为属性的名称（Hibernate 映射列时，若遇到驼峰拼写，会自动添加 _ 连接并将大写字母改成小写）。
	 *	unique	           boolean	列的值是否是唯一的。这是 @UniqueConstraint 注解的一个快捷方式， 实质上是在声明唯一约束。默认值为 false。
	 *	nullable	       boolean	列的值是否允许为 null。默认为 true。
	 *	insertable         boolean	列是否包含在 INSERT 语句中，默认为 true。
	 *	updatable		   boolean	列是否包含在 UPDATE 语句中，默认为 true。
	 *	columnDefinition   String	生成列的 DDL 时使用的 SQL 片段。默认使用推断的类型来生成 SQL 片段以创建此列。
	 *	table			   String	当前列所属的表的名称。
	 *	length				int		列的长度，仅对字符串类型的列生效。默认为255。
	 *	precision			int		列的精度，仅对十进制数值有效，表示有效数值的总位数。默认为0。
	 *	scale				int		列的精度，仅对十进制数值有效，表示小数位的总位数。默认为0。
	 */
	/** @Temporal
	 * 	参数			类型					描述					
	 *							TemporalType.DATE（日期）
	 *	value	TemporalType	TemporalType.TIME（时间）
	 *							TemporalType.TIMESTAMP（日期和时间）
	 *	对应使用 LocalDate LocalTime LocalDateTime
	 */
	/**
	 *  @Transient表示不会持久化入数据库无参
	 */
	/**@Table
	 *  name	String	表的名称，默认为实体名称（参考 @Entity 注解的 name 参数说明），因此如果实体名称与映射的表名称一致时，@Table 注解常常可以省略。
	 *  catalog	String	默认为数据库系统缺省的 catalog。
	 *  schema	String	默认为用户缺省的 schema。
     *  uniqueConstraints	UniqueConstraint[]	表的唯一约束（除了由 @Column 和 @JoinColumn 注解指定的约束以及主键的约束之外的约束），通过使用 @UniqueConstraint 注解来声明，仅在允许自动更新数据库表结构的场景中起到作用，默认没有其他额外的约束条件。
     *  indexes	Index[]	表的索引，通过使用 @Index 注解来声明，仅在允许自动更新数据库表结构的场景中起到作用，默认没有其他额外的索引。
	 */
	/**
	 * @Basic 是属性或方法级别的注解，该注解可以应用于任何以下类型的实体类属性：
	 *	Java 原始类型
	 *	原始类型的包装类型
	 *	String
	 *	java.math.BigInteger
	 *	java.math.BigDecimal
	 *	java.util.Date
	 *	java.util.Calendar
	 *	java.sql.Date
	 *	java.sql.Time
	 *	java.sql.Timestamp
	 *	byte[]
	 *	Byte[]
	 *	char[]
	 *	Character[]
	 *	枚举
	 *	任意实现 java.io.Serializable 接口的类型
	 *	在实体类中，对以上这些类型的属性，如果没有标注 @Basic 注解，则将使用 @Basic 注解的默认值。
	 *		  				属性值的加载策略。可选值：
	 *						FetchType.EAGER：即时加载；
	 *	fetch	FetchType	FetchType.LAZY：延迟加载，当第一次访问属性时才进行数据的加载；
	 *						默认为 FetchType.EAGER。
	 *						optional	boolean	是否允许为 null，默认为 true。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private long id;
	private String name;
	private String pass;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
