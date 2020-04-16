<?php

namespace TransportBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Traffectation
 *
 * @ORM\Table(name="traffectation")
 * @ORM\Entity(repositoryClass="TransportBundle\Repository\TraffectationRepository")
 */
class Traffectation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="ClassBundle\Entity\Classe")
     * @ORM\JoinColumn(name="grade",referencedColumnName="id")
     *
     */
    private $grade;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="Driver")
     * @ORM\JoinColumn(name="driver",referencedColumnName="id")
     *
     */
    private $driver;

    /**
     * @var int
     * @ORM\ManyToOne(targetEntity="Vehicule")
     * @ORM\JoinColumn(name="vehicule",referencedColumnName="id")
     *
     */
    private $vehicule;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set driver
     * @param \TransportBundle\Entity\Driver $driver
     *
     * @return Traffectation
     */
    public function setDriver(\TransportBundle\Entity\Driver $driver = null)
    {
        $this->driver = $driver;

        return $this;
    }

    /**
     * Get driver
     *
     * @return \TransportBundle\Entity\Driver
     */
    public function getDriver()
    {
        return $this->driver;
    }





    /**
     * Set grade
     * @param \ClassBundle\Entity\Classe $grade
     *
     * @return Traffectation
     */
    public function setGrade(\ClassBundle\Entity\Classe $grade = null)
    {
        $this->grade = $grade;

        return $this;
    }

    /**
     * Get grade
     *
     * @return \ClassBundle\Entity\Classe
     */
    public function getGrade()
    {
        return $this->grade;
    }



    /**
     * Set vehicule
     * @param \TransportBundle\Entity\Vehicule $vehicule
     *
     * @return Traffectation
     */
    public function setVehicule(\TransportBundle\Entity\Vehicule $vehicule = null)
    {
        $this->vehicule = $vehicule;

        return $this;
    }

    /**
     * Get vehicule
     *
     * @return \TransportBundle\Entity\Vehicule
     */
    public function getVehicule()
    {
        return $this->vehicule;
    }



}

