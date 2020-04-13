<?php

namespace FirstBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Menu
 *
 * @ORM\Table(name="menu")
 * @ORM\Entity
 */
class Menu
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
     * @ORM\Column(name="date_day", type="string", length=10, nullable=false)
     */
    private $dateDay;

    /**
     * @var string
     *
     * @ORM\Column(name="plate1", type="string", length=255, nullable=false)
     */
    private $plate1;

    /**
     * @var string
     *
     * @ORM\Column(name="plate2", type="string", length=255, nullable=false)
     */
    private $plate2;

    /**
     * @var string
     *
     * @ORM\Column(name="plate3", type="string", length=255, nullable=false)
     */
    private $plate3;


}

