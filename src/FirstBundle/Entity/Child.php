<?php

namespace FirstBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Child
 *
 * @ORM\Table(name="child")
 * @ORM\Entity
 */
class Child
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="lastname", type="string", length=255, nullable=false)
     */
    private $lastname;

    /**
     * @var string
     *
     * @ORM\Column(name="firstname", type="string", length=255, nullable=false)
     */
    private $firstname;

    /**
     * @var string
     *
     * @ORM\Column(name="level", type="string", length=255, nullable=false)
     */
    private $level;

    /**
     * @var integer
     *
     * @ORM\Column(name="parent_id", type="integer", nullable=true)
     */
    private $parentId = 'NULL';

    /**
     * @var integer
     *
     * @ORM\Column(name="class_id", type="integer", nullable=true)
     */
    private $classId = 'NULL';

    /**
     * @var integer
     *
     * @ORM\Column(name="save_nurs_id", type="integer", nullable=true)
     */
    private $saveNursId = 'NULL';

    /**
     * @var integer
     *
     * @ORM\Column(name="save_resto_id", type="integer", nullable=true)
     */
    private $saveRestoId = 'NULL';

    /**
     * @var integer
     *
     * @ORM\Column(name="age", type="integer", nullable=false)
     */
    private $age;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255, nullable=false)
     */
    private $photo;


}

