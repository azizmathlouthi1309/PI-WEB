<?php

namespace RestoNurseryBundle\Entity;

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

    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getDateDay()
    {
        return $this->dateDay;
    }

    /**
     * @param string $dateDay
     */
    public function setDateDay($dateDay)
    {
        $this->dateDay = $dateDay;
    }

    /**
     * @return string
     */
    public function getPlate1()
    {
        return $this->plate1;
    }

    /**
     * @param string $plate1
     */
    public function setPlate1($plate1)
    {
        $this->plate1 = $plate1;
    }

    /**
     * @return string
     */
    public function getPlate2()
    {
        return $this->plate2;
    }

    /**
     * @param string $plate2
     */
    public function setPlate2($plate2)
    {
        $this->plate2 = $plate2;
    }

    /**
     * @return string
     */
    public function getPlate3()
    {
        return $this->plate3;
    }

    /**
     * @param string $plate3
     */
    public function setPlate3($plate3)
    {
        $this->plate3 = $plate3;
    }


}

